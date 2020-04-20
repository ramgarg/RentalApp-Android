package com.rental.login.model.repositry

import androidx.lifecycle.MutableLiveData
import com.rental.login.model.modelclass.ForgotPasswordRequest
import com.rental.login.model.modelclass.ForgotPasswordResponse
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.ServiceGenrator
import retrofit2.Call
import retrofit2.Response

class ForgotPasswordRespository() {
    private var apiclient: LoginAPI? = null

    init {
        apiclient = ServiceGenrator.client.create(LoginAPI::class.java)
    }
    fun forgotPasswordAPI(forgotPasswordRequest: ForgotPasswordRequest){
        val data: MutableLiveData<ForgotPasswordResponse> = MutableLiveData<ForgotPasswordResponse>()
             val call = apiclient?.forgotPassword(forgotPasswordRequest)
             call?.enqueue(object : retrofit2.Callback<ForgotPasswordResponse> {
                 override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                     data.value=null
                 }

                 override fun onResponse(call: Call<ForgotPasswordResponse>, response: Response<ForgotPasswordResponse>) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                data.value=response.body()
                 }

             })
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}