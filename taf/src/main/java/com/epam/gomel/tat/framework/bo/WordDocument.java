package com.epam.gomel.tat.framework.bo;

import com.epam.gomel.tat.framework.factory.Configuration;

public class WordDocument {
    private String name;
    private String text;

    public WordDocument() {
        setName();
        setText();
    }

    public String getName() {
        return name;
    }

    private void setName() {
        name = "File" + System.currentTimeMillis();
    }

    public String getText() {
        return text;
    }

    private void setText() {
        text = Configuration.DOCUMENT_TEXT;
    }
}
