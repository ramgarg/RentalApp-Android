package com.rental.customer.login.model.repositry

import com.rental.customer.login.presenter.RegistrationView
import com.rental.webservice.APIServices

class RegistrationRepository(registrationView: RegistrationView) {

    private  var registrationView: RegistrationView
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