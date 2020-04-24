package com.eazyrento.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.eazyrento.login.model.modelclass.LoginUserResModel
import com.eazyrento.login.model.repositry.LoginUserRepo


class LoginUserViewModel :ViewModel() {

    fun loginUser(LoginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        return LoginUserRepo().loginAPI(LoginUserReqModel)
    }
}