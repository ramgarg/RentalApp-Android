package com.eazyrento.supporting

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.R
import com.eazyrento.Session
import com.eazyrento.common.view.BaseActivity
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
    fun onLocalLanguuage(activity: BaseActivity,language: String){
        activity.onChangeLanguage(language)

        Session.getInstance(activity)?.saveLocalLanguage(language)

        activity.restartCurrentActivity()
    }

     fun languageSpinnerData(activity: BaseActivity) {
        val appLanguage = activity.resources.getStringArray(R.array.SelectLanguage)

        val spinner = activity.findViewById<Spinner>(R.id.sp_language)
        if (spinner != null) {
            val adapter = ArrayAdapter(activity, R.layout.spinner_item_style, appLanguage)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                    if (position == 0)
                        return

                    val language = if(position==1){
                       english_lang_code
                    }
                    else{
                        arebic_lang_code
                    }

                    onLocalLanguuage(activity,language)

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

}

}
