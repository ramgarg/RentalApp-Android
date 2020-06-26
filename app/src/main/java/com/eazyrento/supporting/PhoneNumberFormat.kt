package com.eazyrento.supporting

import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.utils.Common
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import java.lang.Exception
import java.util.regex.Pattern

class PhoneNumberFormat(val context: Context) {

     var phoneNumber: Phonenumber.PhoneNumber?=null
    private var util: PhoneNumberUtil? = null
     lateinit var countryRegionCode: String
    private var phoneFormattingTW: PhoneNumberFormattingTextWatcher? = null

    init {
        if (util == null) {
            util = PhoneNumberUtil.createInstance(context)
            countryRegionCode = context.resources.configuration.locales[0].country

        }
    }


    fun getCountryCodeForLocalRegion(): Int? {

        val code = util?.getCountryCodeForRegion(countryRegionCode)
        return code
    }

    fun getRegionCodeForCountryCode(intCode: Int): String? {
        return util?.getRegionCodeForCountryCode(intCode)
    }


    fun parseNumber(number: String, region: String): Phonenumber.PhoneNumber? {
        return util?.parse(number, region)
    }

    private fun addTextListener(ed_phone: EditText, ed_code: EditText) {
        if (phoneFormattingTW == null) {

            val code = removePlusChar(ed_code)

            phoneFormattingTW = PhoneNumberFormattingTextWatcher(
                getRegionCodeForCountryCode(
                    code.toInt()
                )
            )
            ed_phone.addTextChangedListener(phoneFormattingTW)
        }
    }

    //Phone number wather edit text
    fun phoneNumberListener(context: Context, ed_phone: EditText, ed_code: EditText) {

        ed_phone.setOnFocusChangeListener { v: View?, hasFocus: Boolean ->

            if (isValidCountryCode(ed_code.text.toString())) {
                addTextListener(ed_phone, ed_code)
            }

        }

    }

     fun removePlusChar(ed_code: EditText): String {
        return ed_code.text.removePrefix("+").toString()

    }

    //Phone number wather edit text
    fun phoneCountryCodeNumberListener(ed_code: EditText) {

        ed_code.setOnFocusChangeListener { v: View?, hasFocus: Boolean ->
            phoneFormattingTW = null
        }

        ed_code.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                AppBizLogger.log(
                    AppBizLogger.LoggingType.DEBUG,
                    s.toString().plus("-start-").plus(start).plus("-after-").plus(before)
                        .plus("-count-").plus(count)
                )

                if (start == 0 && s != null && !s.contains("+")) {
                    ed_code.setText("+")
                    ed_code.setSelection(1)
                }
            }

        })
    }


    fun isValidCountryCode(code: String): Boolean {
        if (code.isEmpty() || code.length <= 1) {
            Common.showToast(context, ValidationMessage.COUNTRY_CODE_VALIDATION)
            return false
        } else
            return true
    }

    fun parseNumberWithoutCountryCode(number: String):Phonenumber.PhoneNumber? {

        try {

             phoneNumber = util?.parse(number, "")

            AppBizLogger.log(
                AppBizLogger.LoggingType.DEBUG,
                "phoneNumber:".plus(phoneNumber.toString())
                    .plus("-Country code:".plus(phoneNumber?.countryCode))
            )

            return phoneNumber

        } catch (e: NumberParseException) {
            //Common.showToast(context, e.toString())
            return null
        }

    }

    fun parseNumberWithCountryCode(number: String,region: String): Phonenumber.PhoneNumber? {

        try {
//            val util = PhoneNumberUtil.createInstance(ctx)

             phoneNumber = util?.parse(number, region)

            AppBizLogger.log(
                AppBizLogger.LoggingType.DEBUG,
                "phoneNumber:".plus(phoneNumber.toString())
                    .plus("-Country code:".plus(phoneNumber?.countryCode))
            )

            return phoneNumber

        } catch (e: NumberParseException) {
            Common.showToast(context, e.toString())
            return null
        }

    }

    fun  isValidPhoneNumber(number:Phonenumber.PhoneNumber?): Boolean {
        if (number==null)
            return false

        util?.let {
            return  it.isValidNumber(number)

        }
      return false
    }


}

fun isValidPhoneNumber(phone: String,context: Context): Boolean {

    val bool = android.util.Patterns.PHONE.matcher(phone).matches() &&
    phone.length>context.resources.getInteger(R.integer.phone_min_len) &&
            phone.length<=context.resources.getInteger(R.integer.phone_max_len)

    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "phone no length: ${phone.length}")
    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "isValidPhoneNumber: $bool")
    return bool

}


class PhoneTextWatcher(val phoneNumberFormat: PhoneNumberFormat, val editText: EditText) :
    PhoneNumberFormattingTextWatcher() {

    private val pattern = "[0-9]+"


    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        super.onTextChanged(s, start, before, count)

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        super.beforeTextChanged(s, start, count, after)
    }

    override fun afterTextChanged(s: Editable?) {
        super.afterTextChanged(s)
        try {
            if (Pattern.matches(pattern, s.toString())) {
                editText.setText(
                    "+".plus(phoneNumberFormat.getCountryCodeForLocalRegion()).plus(s.toString())
                )
                editText.setSelection(editText.text.toString().length)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}