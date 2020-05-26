package com.eazyrento.login.view

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.eazyrento.EazyRantoApplication
import com.eazyrento.R
import com.eazyrento.Session
import com.squareup.picasso.Picasso

class ProfileData {

    fun setData(context:Activity){

        val userProfile = EazyRantoApplication.profileData

        userProfile?.let { 
        context.findViewById<TextView>(R.id.tv_merchant_name).text =it.full_name.capitalize()
            context.findViewById<TextView>(R.id.tv_user_type).text = Session.getInstance(context)?.getUserRole()?.capitalize()
            context.findViewById<TextView>(R.id.merchant_email).text=it.email
            context.findViewById<TextView>(R.id.merchant_phone).text=it.mobile_number
            context.findViewById<TextView>(R.id.merchant_address).text=it.address_info.address_line+" "+it.address_info.state+" "+it.address_info.country

        Picasso.with(context).load(it.profile_image).into(context.findViewById<ImageView>(R.id.profile_img))
      }
    }
}