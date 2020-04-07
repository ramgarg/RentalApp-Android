package com.rental.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.login.model.modelclass.LoginUserReqModel
import com.rental.login.model.modelclass.LoginUserResModel
import com.rental.login.model.repositry.LoginUserRepo


class LoginUserViewModel :ViewModel() {

    fun loginUser(LoginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        return LoginUserRepo().loginAPI(LoginUserReqModel)
    }
}