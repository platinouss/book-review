package com.platinouss.bookrecommend.support;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MyCrawler {
    static final String key = "class";
    static final String categoryValue = "bookBasicInfo_info_detail__I0Fx5";

    public String categoryCrawler(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements e1 = doc.getElementsByAttributeValue(key, categoryValue);

        return e1.get(0).text();
    }
}
