package com.example.cobe.cryptonews.common.extensions

import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by cobe on 13/04/2018.
 */

interface Mappable<out R> {

    fun mapToData(): R
}

fun <T : Mappable<R>, R> Call<T>.processRequest(callback: ArticlesInteractorInterface.ResponseInterface<R>) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.run { callback.onArticlesSuccess(mapToData()) }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback.onArticlesError()
        }
    })
}