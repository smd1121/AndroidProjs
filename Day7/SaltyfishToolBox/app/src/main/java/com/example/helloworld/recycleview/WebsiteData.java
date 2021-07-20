package com.example.helloworld.recycleview;

public class WebsiteData {
    String title;
    String url;
    int count;
    public WebsiteData(String title, String url) {
        this.title = title;
        this.url = url;
        this.count = (int) (Math.random() * 10000);
    }

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }
}

