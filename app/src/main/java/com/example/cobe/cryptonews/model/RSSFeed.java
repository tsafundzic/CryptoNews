package com.example.cobe.cryptonews.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by cobe on 29/03/2018.
 */

@Root(name = "rss", strict = false)
public class RSSFeed {

    @Element(name = "title")
    @Path("channel")
    private String title;

    @Element(name = "link")
    @Path("channel")
    private String link;

    @Element(name = "description")
    @Path("channel")
    private String description;

    @Element(name = "lastBuildDate")
    @Path("channel")
    private String lastBuildDate;

    @ElementList(name = "item", inline = true)
    @Path("channel")
    private List<Article> articles;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
