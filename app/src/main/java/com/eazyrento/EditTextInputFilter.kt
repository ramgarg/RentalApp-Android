package com.eazyrento

import android.text.InputFilter
import com.eazyrento.FilterPattern.Companion.ALLOW_ALPHA_NUMERIC_CHAR
import com.eazyrento.FilterPattern.Companion.ALLOW_ONLY_ALPHA_CHAR

class EditTextInputFilter {

    val filter_full_name = InputFilter {
            source, start, end, dest, dstart, dend ->
            val bool = source.matches(ALLOW_ONLY_ALPHA_CHAR.toRegex())
            if(bool) source else ""
    }

    val filter_Compny_discription = InputFilter {
            source, start, end, dest, dstart, dend ->
        val bool = source.matches(ALLOW_ALPHA_NUMERIC_CHAR.toRegex())
        if(bool) source else ""
    }
}

fun filterAs(enum: FilterEnum): Array<InputFilter> {

    val editTextInputFilter =EditTextInputFilter()

   return when(enum){

        FilterEnum.FULL_NAME->arrayOf(editTextInputFilter.filter_full_name)
        FilterEnum.ALPHANUMERIC->arrayOf(editTextInputFilter.filter_Compny_discription)

    }
}
interface FilterPattern{
    companion object{

       /* ^ : start of string
        [ : beginning of character group
        a-z : any lowercase letter
        A-Z : any uppercase letter
        0-9 : any digit
        _ : underscore
        ] : end of character group
        * : zero or more of the given characters
        $ : end of string */

        const val ALLOW_ALPHA_NUMERIC_CHAR =  "^[a-zA-Z0-9_ ]*$"
        const val ALLOW_ONLY_ALPHA_CHAR =  "^[a-zA-Z ]*$"
    }

}
enum class FilterEnum{
    FULL_NAME,ALPHANUMERIC
}