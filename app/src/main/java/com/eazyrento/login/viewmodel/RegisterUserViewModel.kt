package com.eazyrento.login.viewmodel

import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.Validator
import com.eazyrento.login.model.modelclass.*
import com.eazyrento.login.model.repositry.DashboardUserRepo
import com.eazyrento.login.model.repositry.ProfileUserRepo
import com.eazyrento.login.model.repositry.RegisterUserRepo
import com.eazyrento.login.model.repositry.UpdateProfileUserRepo
import com.eazyrento.login.presenter.RegistrationView

class RegisterUserViewModel:ViewModel() {
    fun registerUser(registerUserReqModel: RegisterUserReqModel): LiveData<DataWrapper<RegisterUserResModel>> {
        return RegisterUserRepo().registrationAPI(registerUserReqModel)
    }
}

class ProfileUserViewModel:ViewModel() {
    fun getProfileUser(): LiveData<DataWrapper<ProfileModelReqRes>> {
        return ProfileUserRepo().getProfile()
    }
}

class UpdateProfileUserViewModel:ViewModel() {
    fun getProfileUser(userProfile: UserProfile): LiveData<DataWrapper<JsonElement>> {
        return UpdateProfileUserRepo().updateProfile(userProfile)
    }
}

class DashboardUserViewModel:ViewModel() {
    fun geUserDashboard(): LiveData<DataWrapper<DashboardModel>> {
        return DashboardUserRepo().getDashboard()
    }
}

