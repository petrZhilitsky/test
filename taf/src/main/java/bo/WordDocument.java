package bo;

import yandex_disk.service.Configuration;

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
