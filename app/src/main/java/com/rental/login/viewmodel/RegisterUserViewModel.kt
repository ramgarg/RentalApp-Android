package com.rental.login.viewmodel

import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.customer.utils.Common
import com.rental.customer.utils.Validator
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.model.modelclass.RegisterUserResModel
import com.rental.login.model.repositry.RegisterUserRepo
import com.rental.login.presenter.RegistrationView

class RegisterUserViewModel:ViewModel() {

    lateinit var mutableLiveData: LiveData<DataWrapper<RegisterUserResModel>>

    fun registerUser(registerUserReqModel: RegisterUserReqModel): LiveData<DataWrapper<RegisterUserResModel>> {
        return RegisterUserRepo().registrationAPI(registerUserReqModel)
    }

}