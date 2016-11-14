package com.example.dragrecyclerview.Bean;

/**
 * Created by ZHAO on 2016/11/14.
 */
public class Item {
    private String mDate;
    private String mContent;

    public Item(String date, String content) {
        this.mDate=date;
        this.mContent=content;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
