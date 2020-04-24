package com.eazyrento.common.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.eazyrento.R

class CustomShap{

    fun getRactangleShap(activity: FragmentActivity){
        val  gradientDrawable :GradientDrawable = activity.getDrawable(R.drawable.shap_rectangle_grren) as GradientDrawable
//        drawer.setBounds()
//       drawer.

        gradientDrawable.setColor(ContextCompat.getColor(activity, R.color.colorPrimary))
        gradientDrawable.setShape(GradientDrawable.LINE)
        gradientDrawable.setStroke(12, Color.CYAN)
    }

}