package com.example.cobe.cryptonews.model

import com.example.cobe.cryptonews.common.extensions.Mappable

/**
 * Created by cobe on 13/04/2018.
 */

data class ArticlesResponse(var articles: List<Article>) : Mappable<List<Article>> {

    override fun mapToData(): List<Article> = articles
}
