package com.rental.customer.login.model.repositry

import androidx.lifecycle.MutableLiveData
import com.rental.customer.forgotpassword.model.modelClass.OTPRequest
import com.rental.customer.forgotpassword.model.modelClass.OTPResponse
import com.rental.customer.webservice.APIServices
import com.rental.customer.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class OTPRepository() {

//    private  var otpView:OTPView
    private var apiclient: APIServices? = null

    init {
//      this.otpView=otpView
        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun otpVerifyAPI(otpRequest: OTPRequest){
        val data: MutableLiveData<OTPResponse> = MutableLiveData<OTPResponse>()
             val call = apiclient?.otp(otpRequest)
             call?.enqueue(object : retrofit2.Callback<OTPResponse> {
                 override fun onFailure(call: Call<OTPResponse>, t: Throwable) {
                     data.value=null
                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                 }

                 override fun onResponse(call: Call<OTPResponse>, response: Response<OTPResponse>) {
//                     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                 data.value=response.body()
                 }

             })
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}