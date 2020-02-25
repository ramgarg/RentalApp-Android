package com.rental.customer.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ViewVisibility {

    companion object {

        fun isVisibleOrNot(img_back: ImageView, img_menu: ImageView,
                           img_notification: ImageView,toolbar_title:TextView,title:String) {
            img_back.visibility = View.VISIBLE
            img_menu.visibility = View.GONE
            img_notification.visibility = View.GONE
            toolbar_title.text=title
        }
    }
}