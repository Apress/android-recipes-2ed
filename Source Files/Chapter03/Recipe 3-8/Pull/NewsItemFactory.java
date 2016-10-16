package com.examples.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class NewsItemFactory {

    /* Data Model Class */
    public static class NewsItem {
        public String title;
        public String link;
        public String description;
        
        @Override
        public String toString() {
            return title;
        }
    }
    
    /*
     * Parse the RSS feed out into a list of NewsItem elements
     */
    public static List<NewsItem> parseFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<NewsItem> items = new ArrayList<NewsItem>();
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
                
            if (parser.getName().equals("rss") ||
                    parser.getName().equals("channel")) {
                //Skip these items, but allow to drill inside
            } else if (parser.getName().equals("item")) {
                NewsItem newsItem = readItem(parser);
                items.add(newsItem);
            } else {
                //Skip any other elements and their children
                skip(parser);
            }
        }
        
        return items;
    }
    
    /*
     * Parse each <item> element in the XML into a NewsItem
     */
    private static NewsItem readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        NewsItem newsItem = new NewsItem();
        
        //Must start with an <item> element to be valid
        parser.require(XmlPullParser.START_TAG, null, "item");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            
            String name = parser.getName();
            if (name.equals("title")) {
                parser.require(XmlPullParser.START_TAG, null, "title");
                newsItem.title = readText(parser);
                parser.require(XmlPullParser.END_TAG, null, "title");
            } else if (name.equals("link")) {
                parser.require(XmlPullParser.START_TAG, null, "link");
                newsItem.link = readText(parser);
                parser.require(XmlPullParser.END_TAG, null, "link");                
            } else if (name.equals("description")) {
                parser.require(XmlPullParser.START_TAG, null, "description");
                newsItem.description = readText(parser);
                parser.require(XmlPullParser.END_TAG, null, "description");
            } else {
                //Skip any other elements, and their children
                skip(parser);
            }
        }
        
        return newsItem;
    }
    
    /*
     * Read the text content of the current element, which is the data contained
     * between the start and end tag
     */
    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    
    /*
     * Helper method to skip over the current element and any children
     * it may have underneath it
     */
    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        
        /*
         * For every new tag, increase the depth counter.  Decrease it for each tag's end and
         * return when we have reached an end tag that matches the one we started with.
         */
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
    }
}
