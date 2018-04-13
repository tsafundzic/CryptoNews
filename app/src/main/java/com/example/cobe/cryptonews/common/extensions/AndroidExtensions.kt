package com.example.cobe.cryptonews.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by cobe on 13/04/2018.
 */


inline fun <reified T : Activity> Context.getIntent() = Intent(this, T::class.java)