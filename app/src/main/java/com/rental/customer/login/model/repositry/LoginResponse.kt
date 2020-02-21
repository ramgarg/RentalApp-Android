package com.rental.customer.login.model.repositry

import com.rental.customer.presenter.LoginInterface
import com.rental.customer.webservice.APIServices

class LoginResponse(loginInterface: LoginInterface) {

    private  var loginInterface:LoginInterface
    private var apiclient: APIServices? = null

    init {
      this.loginInterface=loginInterface
//        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun loginAPI(){
        loginInterface.showToast("Here LoginAPI will be called")
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