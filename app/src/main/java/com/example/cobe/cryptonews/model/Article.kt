package com.example.cobe.cryptonews.model

/**
 * Created by cobe on 13/04/2018.
 */
class Article {

    val author: String
    var title: String
    val description: String
    val url: String
    val urlToImage: String
    val publishedAt: String

    constructor(author: String, title: String, description: String, url: String, urlToImage: String, publishedAt: String) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
    }
}