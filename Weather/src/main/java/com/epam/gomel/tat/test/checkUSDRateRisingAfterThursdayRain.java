package com.epam.gomel.tat.test;

import com.epam.gomel.tat.framework.bo.Currency;
import com.epam.gomel.tat.framework.loger.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class checkUSDRateRisingAfterThursdayRain {
    private static final String WEATHER_URL = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=34395593e0cb4adc87c200305200203&q=Homyel&format=xml&date=%s&enddate=%s";
    private static final String CURRENCY_URL = "http://www.nbrb.by/API/ExRates/Rates/145?onDate=";
    private static final String XPATH_EXPRESSION = "//weather/mintempC[not(contains(text(),'-'))]/following-sibling::hourly/precipMM[not(contains(text(),'0.0'))]/parent::hourly/parent::weather/date";
    private int thursdayRainCount = 0;
    private int fridayRateIncreaseCount = 0;

    @Test
    public void usdRateAfterThursdayRainTest() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, ParseException {
        Calendar calendar = new GregorianCalendar(2017, 0, 1);
        while (calendar.get(Calendar.YEAR) != 2018) {
            String dateFrom = calendarToString(calendar);
            getLastMonthDay(calendar);
            String dateTo = calendarToString(calendar);
            Log.info("Request weather data for a period: " + dateFrom + " - " + dateTo);
            InputStream response = given().when().get(String.format(WEATHER_URL, dateFrom, dateTo)).asInputStream();
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            documentFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(response);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            Log.info("Search for rainy days in responce");
            XPathExpression xPathExpression = xpath.compile(XPATH_EXPRESSION);
            Object result = xPathExpression.evaluate(document, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            for (int i = 0; i < nodeList.getLength(); i++) {
                usdRiseCheck(nodeList.item(i).getTextContent());
            }
            calendar.add(Calendar.DATE, 1);
        }
        Log.info("Count USD rate rising after thursdays' rain percentage");
        double matchPercentage = 0;
        if (thursdayRainCount != 0) {
            matchPercentage = (fridayRateIncreaseCount / (double) thursdayRainCount) * 100;
        }
        Assert.assertTrue(matchPercentage > 50.0, "USD rate rises after thursday rain in less than 50% cases");
    }

    private String calendarToString(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private Calendar stringToCalendar(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar;
    }

    private Calendar getLastMonthDay(Calendar date) {
        date.add(Calendar.MONTH, 1);
        date.set(Calendar.DATE, 1);
        date.add(Calendar.DATE, -1);
        return date;
    }

    private void usdRiseCheck(String nodeDate) throws ParseException {
        Calendar currentDate = stringToCalendar(nodeDate);
        if (currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Log.info("Request USD rate rising after " + currentDate.getTime());
            thursdayRainCount++;
            Currency thursdayRate = get(CURRENCY_URL + calendarToString(currentDate)).as(Currency.class);
            currentDate.add(Calendar.DATE, 1);
            Currency fridayRate = get(CURRENCY_URL + calendarToString(currentDate)).as(Currency.class);
            if (fridayRate.getRate() > thursdayRate.getRate()) {
                Log.info("USD rate rised");
                fridayRateIncreaseCount++;
            } else {
                Log.info("USD rate didn't rise");
            }
        }
    }
}
