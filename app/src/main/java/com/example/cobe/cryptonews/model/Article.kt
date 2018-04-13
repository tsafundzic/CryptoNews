package com.example.cobe.cryptonews.model

/**
 * Created by cobe on 13/04/2018.
 */

data class Article(val author: String,
                   val title: String,
                   val description: String,
                   val url: String,
                   val urlToImage: String,
                   val publishedAt: String)