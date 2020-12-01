package com.eazyrento.appbiz

import android.content.Context
import com.eazyrento.R

const val currencySpace = " "

fun Context.amountWithCurrencyName(amountDouble: Double?): String {

    return amountWithCurrencyName(amountDouble.toString())
}

fun Context.amountWithCurrencyName(amountString: String):String{
    return amountString.plus(currencySpace.plus(getString(R.string.currency_name)))
}