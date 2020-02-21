package com.rental.customer.registration.model.repositry

import com.rental.customer.otp.presenter.OTPView
import com.rental.customer.presenter.LoginInterface
import com.rental.customer.registration.presenter.RegistrationView
import com.rental.customer.utils.APIServices

class RegistrationResponse(registrationView: RegistrationView) {

    private  var registrationView:RegistrationView
    private var apiclient: APIServices? = null

    init {
      this.registrationView=registrationView
//        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun registrationAPI(){
        registrationView.moveToOtp()
        registrationView.showToast("Registration API will be called")
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