package com.eazyrento.common.view

interface UserInfoAPP {

    companion object{
        // User roll type
        const val CUSTOMER = "customer"
        const val AGENT = "agent"
        const val MERCHANT = "merchant"
//        var user_role:String?=null

        // User login source facebook/google/normal
        const val BY_NORMAL = "normal"
        const val BY_FACEBOOK = "facebook"
        const val BY_GOOGLE = "google"
        var REGISTRATIONS_SOURCE :String?=null

        //Source
        const val SOURCE = "mobile_app"
    }

}