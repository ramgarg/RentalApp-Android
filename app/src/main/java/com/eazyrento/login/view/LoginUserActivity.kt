package com.eazyrento.login.view

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
import com.eazyrento.supporting.*
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.ed_password


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

    //user login sucess
    private lateinit var loginUserResModel:LoginUserResModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        languageSpinnerData()

        phoneNumberFormat = PhoneNumberFormat(this)

        ed_email.addTextChangedListener(PhoneTextWatcher(phoneNumberFormat,ed_email))

        // vallidation for if user is already login
         isLoginUser(intent)


    }

    private fun isLoginUser(intent:Intent){
        if (EazyRantoApplication.isUserLogin())
            sendUserReleventPanel(intent,Session.getInstance(EazyRantoApplication.context)?.getUserRole())
        else
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,Constant.USER_NEED_LOGIN)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, "onNewIntent")
        if (isDeeplinkingFromNotification(intent)){
            isLoginUser(intent!!)
        }
    }

    // lisetner
    fun onRegisterClick(view: View) {
        MoveToAnotherComponent.moveToActivityNormal<RegistrationUserActivity>(this)
    }

    fun onForgotPasswordClick(view: View) {
        MoveToAnotherComponent.moveToActivityNormal<ForgotPasswordActivity>(this)
    }

    fun onLoginClick(view: View) {

        if (isValidCredintitial(ed_email, ed_password).not()) {
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
        showToast(R.string.SOCIAL_FAILED)

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

    private fun sendUserReleventPanel(intent:Intent,userRole: String?) {

        when (userRole) {

            UserInfoAPP.AGENT -> MoveToAnotherComponent.startActivityForResult<AgentMainActivity>(this,
                Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK,Constant.DEEPLINK_VALUE,isDeeplinkingFromNotification(intent))

            UserInfoAPP.CUSTOMER ->  MoveToAnotherComponent.startActivityForResult<CustomerMainActivity>(this,
                Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK,Constant.DEEPLINK_VALUE,isDeeplinkingFromNotification(intent))

            UserInfoAPP.MERCHANT ->  MoveToAnotherComponent.startActivityForResult<MerchantMainActivity>(this,
                Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK,Constant.DEEPLINK_VALUE,isDeeplinkingFromNotification(intent))
        }

    }

    private fun createLoginUserReqModel(
        password: String,
        email: String,
        byUser: String
    ): LoginUserReqModel {

        val deviceInfo = DeviceInfo(
            Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID),BuildConfig.VERSION_NAME,null,
                Build.VERSION.CODENAME,Constant.PLATFORM, Session.getInstance(this)?.getPushNotificationToken())

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

            loginUserResModel = data

            Session.getInstance(EazyRantoApplication.context)
                ?.saveUserRole(loginUserResModel.user_info.user_role)

            EazyRantoApplication.saveAccesesToken(loginUserResModel.user_info)

            ProfileData().profileAPFromILogin(this, object : LoginUserStatus {
                override fun onUser(user: LoginUserStatus.UserStatus) {
                        when(user){
                            LoginUserStatus.UserStatus.NOT_FIRST_TIME ->onLoginSucess()
                            else -> {
                                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"First time user")
                            }
                        }
                }
            })
        }
    }

    private fun onLoginSucess(){

        EazyRantoApplication.onLoginUpdateSession(loginUserResModel.user_info)
        sendUserReleventPanel(intent,loginUserResModel.user_info.user_role)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onActivityResult--".plus(requestCode))
        // login finiching on back button press
        if (requestCode == Constant.REQUEST_CODE_FINISH_LOGIN_ON_BACK && resultCode == Activity.RESULT_OK){
             finishCurrentActivity(Activity.RESULT_OK)
        }
        // first time user
        else if(resultCode ==Activity.RESULT_OK && requestCode==Constant.REQUEST_CODE_FINISH_FIRST_TIME_USER){
            // first time user profile update
            onLoginSucess()
        }
        //gmail login
       else if (requestCode == Constant.RC_SIGN_IN_GOOGLE){
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
       else if (resultCode == Activity.RESULT_OK && mCallbackManager != null) {
            mCallbackManager!!.onActivityResult(requestCode, resultCode, data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun languageSpinnerData() {
        val appLanguage = resources.getStringArray(R.array.SelectLanguage)

        val spinner = findViewById<Spinner>(R.id.sp_language)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, appLanguage)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){

                    }
                    else{

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}

interface ChoseUserRole{
    fun onChose(userRole:String)
}