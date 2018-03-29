package com.example.cobe.cryptonews.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by cobe on 29/03/2018.
 */

@Root(name = "item", strict = false)
public class Article {

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "description")
    private String description;

    @Element(name = "enclosure")
    private String image;

    @Element(name = "guid")
    private String guid;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "dc:creator")
    private String creator;

    @Element(name = "category")
    private String category;

    public Article(String title, String link, String description, String image, String guid, String pubDate, String creator, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.guid = guid;
        this.pubDate = pubDate;
        this.creator = creator;
        this.category = category;
    }

    public String getTitle() {
        return title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
