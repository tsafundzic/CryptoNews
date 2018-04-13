package com.example.cobe.cryptonews.common.utils

/**
 * Created by cobe on 13/04/2018.
 */
class ValidationUtils {

    companion object {
        fun isEmpty(text: String): Boolean {
            return (text.isEmpty() && text.trim().isEmpty())
        }
    }
}