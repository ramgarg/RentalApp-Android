package com.eazyrento.supporting

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.utils.Common
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import kotlinx.android.synthetic.main.activity_register_user.*

class PhoneNumberFormat(val context: Context) {

    private var util: PhoneNumberUtil? = null
    private lateinit var countryRegionCode:String
    private var phoneFormattingTW:PhoneNumberFormattingTextWatcher?=null

    init {
        if (util==null){
            util = PhoneNumberUtil.createInstance(context)
            countryRegionCode = context.resources.configuration.locales[0].country

        }
    }


   fun getCountryCodeForLocalRegion():Int?{

        val code = util?.getCountryCodeForRegion(countryRegionCode)
        return code
   }

    fun getRegionCodeForCountryCode(intCode:Int): String? {
        return util?.getRegionCodeForCountryCode(intCode)
    }


   fun parseNumber(number:String,region:String): Phonenumber.PhoneNumber? {
       return util?.parse(number,region)
   }

    private fun addTextListener(ed_phone:EditText,ed_code: EditText){
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
    fun phoneNumberListener(context:Context,ed_phone:EditText,ed_code: EditText){

        ed_phone.setOnFocusChangeListener{
            v: View?, hasFocus: Boolean ->

            if (isValidCountryCode(ed_code.text.toString())) {
                addTextListener(ed_phone,ed_code)
            }

        }

       /* ed_phone.setOnTouchListener(object :View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    if (isValidCountryCode(ed_code.text.toString())) {

                        addTextListener(ed_phone,ed_code)

                    }
                    return false
                }
                return false
            }

        })*/



    }

    private fun removePlusChar(ed_code: EditText): String {
        return ed_code.text.removePrefix("+").toString()

    }

    //Phone number wather edit text
    fun phoneCountryCodeNumberListener(ed_code:EditText){

        ed_code.setOnFocusChangeListener{
                v: View?, hasFocus: Boolean ->
            phoneFormattingTW = null
        }

        ed_code.addTextChangedListener(object :TextWatcher{

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,s.toString().plus("-start-").plus(start).plus("-after-").plus(before).plus("-count-").plus(count))

                if (start==0 && s!=null && !s.contains("+")) {
                    ed_code.setText("+")
                    ed_code.setSelection(1)
                }
            }

        })
    }

    fun parsePhoneNumberWithCountryCode(ed_code: EditText,ed_phone: EditText){
         val code = removePlusChar(ed_code)
        if (isValidCountryCode(code)) {
           val phonenumber =  parseNumber(ed_phone.text.toString(),code)

        }
    }
    fun isValidCountryCode(code:String):Boolean{
        if (code.isEmpty() || code.length<=1){
            Common.showToast(context,ValidationMessage.COUNTRY_CODE_VALIDATION)
            return false
        }else
            return true
    }

}

fun isValidPhoneNumber(phone: String):Boolean{

    val bool =  android.util.Patterns.PHONE.matcher(phone).matches()
    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"vlue----"+bool)
    return bool

}

fun parseCoutryCodeInNumber(number:String,ctx:Context){

    val phn = PhoneNumberUtil.createInstance(ctx)
    val phoneNumber =  phn.parse(number,"")

    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"phoneNumber:".plus(phoneNumber.toString()).plus("-Country code:".plus(phoneNumber.countryCode)))
}