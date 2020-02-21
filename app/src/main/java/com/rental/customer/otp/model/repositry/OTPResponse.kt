package com.rental.customer.otp.model.repositry

import com.rental.customer.otp.presenter.OTPView
import com.rental.customer.webservice.APIServices

class OTPResponse(otpView: OTPView) {

    private  var otpView:OTPView
    private var apiclient: APIServices? = null

    init {
      this.otpView=otpView
//        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun otpVerifyAPI(){
        otpView.showToast("Here OTPAPI will be called")
//             val call = apiclient?.login()
//             call?.enqueue(object : retrofit2.Callback<JsonElement> {
//                 override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                 }
//
//                 override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                 }
//
//             })
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}