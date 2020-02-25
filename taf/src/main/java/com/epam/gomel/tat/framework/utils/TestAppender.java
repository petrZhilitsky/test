package com.epam.gomel.tat.framework.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class TestAppender extends AppenderSkeleton {
    @Override
    protected void append(final LoggingEvent loggingEvent) {
        final StringBuilder eventString = new StringBuilder(layout.format(loggingEvent));
        if (layout.ignoresThrowable()) {
            final String[] strings = loggingEvent.getThrowableStrRep();
            if (strings != null) {
                for (final String value : strings) {
                    eventString.append(value).append(Layout.LINE_SEP);
                }
            }
        }
        Reporter.log(eventString.toString() + "</br>");
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
