package google.framework.service;

import google.framework.model.PriceCalcData;

public class PriceCalcDataCreator {
    public static final String TEST_COMMITMENT_TERM = "commitment.term";
    public static final String TEST_TOTAL_COST = "total.cost";

    public static PriceCalcData withDataFromProperty() {
        return new PriceCalcData(TestDataReader.getTestData(TEST_COMMITMENT_TERM),
                TestDataReader.getTestData(TEST_TOTAL_COST));
    }

    public static PriceCalcData withoutTerm() {
        return new PriceCalcData("", TestDataReader.getTestData(TEST_TOTAL_COST));
    }

    public static PriceCalcData withoutCost() {
        return new PriceCalcData(TestDataReader.getTestData(TEST_COMMITMENT_TERM), "");
    }
}
