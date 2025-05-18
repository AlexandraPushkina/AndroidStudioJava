package com.example.homework13;

import java.io.Serializable;

public class Note implements Serializable {
    private final String title;
    private final String text;
    private final int style;

    public Note (String title, String text, int style) {
        this.title = title;
        this.text = text;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getStyle() {
        return style;
    }

}

