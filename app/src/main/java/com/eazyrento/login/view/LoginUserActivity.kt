package com.eazyrento.login.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.eazyrento.*
import com.eazyrento.agent.view.activity.AgentMainActivity
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.login.model.modelclass.DeviceInfo
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.eazyrento.login.model.modelclass.LoginUserResModel
import com.eazyrento.login.viewmodel.LoginUserViewModel
import com.eazyrento.merchant.view.activity.MerchantMainActivity
import com.eazyrento.supporting.MyJsonParser
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_login.*


class LoginUserActivity : AppBizLogin() {
    //fb permittion
    private companion object {
        const val EMAIL = "email"
        const val PROFILE = "public_profile"
    }

    //fb
    private var mCallbackManager: CallbackManager? = null
    //google
    private var mGoogleSignInClient:GoogleSignInClient?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // vallidation for if user is already login
        if (EazyRantoApplication.isUserLogin())
            sendUserReleventPanel(Session.getInstance(EazyRantoApplication.context)?.getUserRole())


    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onNewIntent")
    }

    // lisetner
    fun onRegisterClick(view: View) {
        MoveToAnotherComponent.moveToActivityNormal<RegistrationUserActivity>(this)
    }

    fun onForgotPasswordClick(view: View) {
        MoveToAnotherComponent.moveToActivityNormal<ForgotPasswordActivity>(this)
    }

    fun onLoginClick(view: View) {

        if (failCheckValdationLoginCredintitial(ed_email, ed_password)) {
            return
        }


        loginAPI(
            createLoginUserReqModel(
                ed_password.text.toString(),
                ed_email.text.toString(),
                UserInfoAPP.BY_NORMAL
            )
        )

    }

    //fb login
    fun onFBClick(view: View) {

        // already login
        if(EazyRantoApplication.isUserLoginWithFB()){
            requestProfileDataToFB(buildUserReqData(UserInfoAPP.BY_FACEBOOK))
            return
        }

        mCallbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().logInWithReadPermissions(this, listOf(EMAIL, PROFILE))

        LoginManager.getInstance().registerCallback(mCallbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, result.toString())
                requestProfileDataToFB(buildUserReqData(UserInfoAPP.BY_FACEBOOK))
            }

            override fun onCancel() {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onCancel")
            }

            override fun onError(error: FacebookException?) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, error.toString())
            }

        })

    }

    fun requestProfileDataToFB(loginUserReqModel: LoginUserReqModel) {

        if(Env.isLogging)
           FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS)

       val request = GraphRequest.newMeRequest(
            AccessToken.getCurrentAccessToken()
        ) { `object`, response ->
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, `object`.toString())
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, response.toString())

            MyJsonParser.parseFBData(`object`, loginUserReqModel)
           choseUserRole(loginUserReqModel)

        }
       val params = Bundle()
           params.putString("fields","id,first_name,last_name,email,name,link")
        request.parameters =params
        request.executeAsync()
    }

    fun buildUserReqData(byUser: String):LoginUserReqModel {

        return createLoginUserReqModel("dummy_password", "EMAIL", byUser)

    }

    fun onGmailClick(view: View) {
        // already login
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account!=null){

            requestProfileDataToGoogle(buildUserReqData(UserInfoAPP.BY_GOOGLE),account)

            return
        }
         //gmail login
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

        startActivityForResult(mGoogleSignInClient?.signInIntent,Constant.RC_SIGN_IN_GOOGLE)

    }
    fun requestProfileDataToGoogle(
        buildUserReqData: LoginUserReqModel,
        account: GoogleSignInAccount?
    ) {
        account?.let {
        buildUserReqData.username = if (account.email==null) account.id!! else account.email!!
        buildUserReqData.full_name = account.displayName
        buildUserReqData.email =account.email

        choseUserRole(buildUserReqData)

            return
        }
        showToast(ValidationMessage.SOCIAL_FAILED)

    }

    fun onSkipLoginClick(view: View) {
       // choseUserRole(null)
    }
    fun choseUserRole(loginUserReqModel: LoginUserReqModel){

        Common.userDialog(this, R.layout.rental_dialog, object : ChoseUserRole {
            override fun onChose(userRole: String) {

                loginUserReqModel.user_role = userRole

                //login api call
                loginAPI(loginUserReqModel)

            }
        }).show()
    }

    // by user login by email, gamail , fb
    private fun loginAPI(loginUserReqModel: LoginUserReqModel) {

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<LoginUserViewModel>(this)
                    .loginUser(loginUserReqModel)
                , this, this
            )
        }


    }

    private fun sendUserReleventPanel(userRole: String?) {

        when (userRole) {

            UserInfoAPP.AGENT -> MoveToAnotherComponent.startActivityForResult<AgentMainActivity>(this,
                Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK,Constant.LOGIN_KEY_FINISH,Constant.LOGIN_VALUE)

            UserInfoAPP.CUSTOMER ->  MoveToAnotherComponent.startActivityForResult<CustomerMainActivity>(this,
                Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK,Constant.LOGIN_KEY_FINISH,Constant.LOGIN_VALUE)

            UserInfoAPP.MERCHANT ->  MoveToAnotherComponent.startActivityForResult<MerchantMainActivity>(this,
                Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK,Constant.LOGIN_KEY_FINISH,Constant.LOGIN_VALUE)
        }

    }

    private fun createLoginUserReqModel(
        password: String,
        email: String,
        byUser: String
    ): LoginUserReqModel {

        val deviceInfo = DeviceInfo(
            Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        )
        return LoginUserReqModel(
            deviceInfo,
            password,
            byUser,
            UserInfoAPP.SOURCE,
            email
        )

    }

    override fun <T> onSuccessApiResult(data: T) {

        if (data is LoginUserResModel) {

            EazyRantoApplication.onLoginUpdateSession(data.user_info)

            sendUserReleventPanel(data.user_info.user_role)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        AppBizLogger.log(
            AppBizLogger.LoggingType.DEBUG,
            "" + requestCode + "--" + resultCode + "---" + data.toString()
        )
        // login finiching on back button press
        if (requestCode == Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK && resultCode == Activity.RESULT_OK){
             finishCurrentActivity(Activity.RESULT_OK)
            return
        }
        //gmail login
        if (requestCode == Constant.RC_SIGN_IN_GOOGLE){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,account.toString())

                requestProfileDataToGoogle(buildUserReqData(UserInfoAPP.BY_GOOGLE),account)

            }catch (ex:ApiException){
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,ex.toString())
            }

        }
        //fb login
        if (resultCode == Activity.RESULT_OK && mCallbackManager != null) {
            mCallbackManager!!.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}

interface ChoseUserRole{
    fun onChose(userRole:String)
}