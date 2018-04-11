package com.example.cobe.cryptonews.presentation

/**
 * Created by cobe on 11/04/2018.
 */
interface BasePresenter<T> {

    fun setView(view: T)
}