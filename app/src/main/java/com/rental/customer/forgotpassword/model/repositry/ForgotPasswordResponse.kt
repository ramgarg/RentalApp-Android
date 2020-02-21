package com.rental.customer.forgotpassword.model.repositry

import com.google.gson.JsonElement
import com.rental.customer.forgotpassword.presenter.ForgotPasswordActivityPresenter
import com.rental.customer.presenter.ForgotPasswordInterface
import com.rental.customer.webservice.APIServices
import retrofit2.Call
import retrofit2.Response

class ForgotPasswordResponse(forgotPasswordInterface: ForgotPasswordInterface) {

    private  var forgotPasswordInterface:ForgotPasswordInterface
    private var apiclient: APIServices? = null

    init {
      this.forgotPasswordInterface=forgotPasswordInterface
//        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun forgotPasswordAPI(forgotPasswordActivityPresenter: ForgotPasswordActivityPresenter){
        forgotPasswordInterface.showToast("Here ForgotPasswordAPI will be called")
             val call = apiclient?.login()
             call?.enqueue(object : retrofit2.Callback<JsonElement> {
                 override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                 forgotPasswordActivityPresenter.onFail()
                 }

                 override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                     forgotPasswordActivityPresenter.onSuccess()
                 }

             })
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}