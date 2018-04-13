package com.example.cobe.cryptonews

import com.example.cobe.cryptonews.api.ApiClient.Companion.getArticleInteractor
import com.example.cobe.cryptonews.presentation.MainInterface
import com.example.cobe.cryptonews.presentation.implementation.MainPresenterImpl

/**
 * Created by cobe on 13/04/2018.
 */


fun mainPresenter(): MainInterface.Presenter = MainPresenterImpl(getArticleInteractor())