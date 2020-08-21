package com.eazyrento.supporting

import android.content.Context
import com.eazyrento.Session
import java.util.*


interface LocalManager{
companion object{
    const val arebic_lang_code = "ar"
    const val english_lang_code ="en"

fun updateResources(
    context: Context,
    language: String
): Context? {


    val locale = Locale(language)
    Locale.setDefault(locale)
    val configuration = context.resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    return context.createConfigurationContext(configuration)


}

}

}