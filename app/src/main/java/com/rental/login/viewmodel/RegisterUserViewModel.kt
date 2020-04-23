package com.rental.login.viewmodel

import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.customer.utils.Common
import com.rental.customer.utils.Validator
import com.rental.login.model.modelclass.ProfileModelReqRes
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.model.modelclass.RegisterUserResModel
import com.rental.login.model.modelclass.UserProfile
import com.rental.login.model.repositry.ProfileUserRepo
import com.rental.login.model.repositry.RegisterUserRepo
import com.rental.login.model.repositry.UpdateProfileUserRepo
import com.rental.login.presenter.RegistrationView

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