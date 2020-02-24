//package com.rental.customer.forgotpassword.presenter
//
//import com.rental.customer.login.model.repositry.ForgotPasswordRespository
//import com.rental.customer.presenter.ForgotPasswordInterface
//import com.rental.customer.utils.Validator
//
//class ForgotPasswordActivityPresenter(forgotPasswordInterface: ForgotPasswordInterface) {
//
//    private var forgotPasswordInterface: ForgotPasswordInterface
//    var forgotPasswordRespository: ForgotPasswordRespository
//    init {
//        this.forgotPasswordInterface=forgotPasswordInterface
//        forgotPasswordRespository= ForgotPasswordRespository(forgotPasswordInterface)
//    }
//
//    fun forgotPasswordAPI(email:String){
//        if(checkValidation(email)){
//            forgotPasswordRespository.forgotPasswordAPI(this)
//        }
//
//    }
//
//    fun onSuccess(){
//        forgotPasswordInterface.onSuccess()
//    }
//
//    fun onFail(){
//        forgotPasswordInterface.onFailed()
//    }
//
//
//}