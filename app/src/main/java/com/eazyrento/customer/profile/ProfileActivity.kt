package com.eazyrento.customer.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.Constant
import com.eazyrento.EazyRantoApplication
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.AppBizLogin
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Validator
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.UpdateProfileUserViewModel
import com.eazyrento.supporting.OnPiclImageToBase64
import com.eazyrento.supporting.UploadImageFromDevice
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppBizLogin() {
    var userProfile: UserProfile? = null

    private val uploadImageFromDevice = UploadImageFromDevice()
    private var selectProfID:String?=null
    private var selectBase64String:String?=null
    private var selectGender:String?=null


    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        topBarWithBackIconAndTitle("Profile")

        userProfile = EazyRantoApplication.profileData
        documentSpinnerData()
        genderSpinnerData()
        setProfileData(userProfile)

        btn_save.setOnClickListener { onClickSaveButton() }
        ed_dob.setOnClickListener {
            Common.dateSelector(this,ed_dob)
        }

        btn_select_location.setOnClickListener {
            MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(this,Constant.ADDRESS_REQUECT_CODE,Constant.INTENT_ADDR_LIST,userProfile?.address_info!!.id)

        }

    }

    private fun setProfileData(userProfile: UserProfile?) {

        ed_full_name.setText(userProfile?.full_name)
        ed_user_name.setText(userProfile?.username_choice)
        ed_email.setText(userProfile?.email)
        ed_country.setText(userProfile?.country_code)
        ed_phone.setText(userProfile?.mobile_number)
        ed_dob.setText(userProfile?.dob)
        ed_company_name.setText(userProfile?.buisness)
        ed_des.setText(userProfile?.description)
        tv_add_country.setText(userProfile?.address_info?.country)
        tv_add_city.setText(userProfile?.address_info?.city)
        tv_add_line.setText(userProfile?.address_info?.address_line)
        Picasso.with(this).load(userProfile?.profile_image).into(img_profile)


    }

    fun onClickSaveButton() {

        updateProfileData()
        userProfile?.let {
            //validation
            if(checkProfileValidation()) {
                updateProfileUserAPI(it)
            }

        }
    }

    private fun checkProfileValidation():Boolean {
        if (ed_full_name.text.toString().isEmpty()) {
            showToast(ValidationMessage.VALID_NAME)
        }
        else if(ed_user_name.text.toString().isEmpty()){
            showToast(ValidationMessage.VALID_USER_NAME)
        }
        else if(ed_user_name.text.toString().length<4){
            showToast(ValidationMessage.VALID_USER_NAME)
        }
        else if(ed_email.text.toString().isEmpty()){
            showToast(ValidationMessage.VALID_EMAIL_ID)
        }
        else if (!Validator.isEmailValid(ed_email.text.toString())) {
            showToast(ValidationMessage.VALID_EMAIL_ID)
            ed_email.requestFocus()
        }
        else if(ed_country.text.toString().isEmpty()){
            showToast(ValidationMessage.COUNTRY_CODE)
        }
        else if(ed_phone.text.toString().isEmpty()){
            showToast(ValidationMessage.PHONE_NUMBER)
        }
        else if(ed_dob.text.toString().isEmpty()){
            showToast(ValidationMessage.DATE_OF_BIRTH)
        }
        else if(ed_company_name.text.toString().isEmpty()){
            showToast(ValidationMessage.COMPANY)
        }
        else if(!sp_gender.isSelected){
            showToast(ValidationMessage.GENDER)
        }
        else if(!sp_select_document.isSelected){
            showToast(ValidationMessage.DOCUMENT)
        }
        else{
            return true
        }
        return false
    }

    private fun updateProfileData() {

        userProfile?.let { it ->
            it.full_name = "" + ed_user_name.text
            it.dob = "" + ed_dob.text
            it.gender = "" + sp_gender.selectedItemId
            it.email = "" + ed_email.text
            it.description = "" + ed_des.text
            it.country_code = "" + ed_country.text
            it.buisness = "" + ed_company_name.text
            it.attached_document = "" + sp_select_document.selectedItemId

            it.id_proof_title = "" + sp_select_document.selectedItem
            it.mobile_number = "" + ed_phone.text
            it.profile_image = "" + img_profile
            it.username_choice = "" + ed_user_name.text

            it.gender = selectGender

            selectBase64String?.let {inner ->
                it.attached_document = inner
                it.id_proof_title = selectProfID!!
            }

        }
    }

    /*
    * update profile
    * */
    fun updateProfileUserAPI(userProfile: UserProfile) {

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<UpdateProfileUserViewModel>(this)
                    .getProfileUser(userProfile)
                , this, this
            )


        }

    }

    override fun <T> onSuccessApiResult(data: T) {
        super.onSuccessApiResult(data)
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            uploadImageFromDevice.onActivityResult(requestCode, resultCode, data)
            return
        }
        if (resultCode== Activity.RESULT_OK && requestCode ==Constant.ADDRESS_REQUECT_CODE){

            val address =data!!.getParcelableExtra<AddressListResModelItem>(Constant.KEY_ADDRESS)

            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,address.toString())

            userProfile?.let {
             it.address_info.address_line = address.address_line as String
            it.address_info.address_type =address.address_type as String
            it.address_info.city = address.city as String
            it.address_info.country = address.country as String
            }

            /*tv_work_location.text = address.address_line+","+address.address_type
            objBookingReqModelItem.address_id = address.id*/
        }
    }

    private fun documentSpinnerData() {
        val document = resources.getStringArray(R.array.RegistrationDocument)

        val spinner = findViewById<Spinner>(R.id.sp_select_document)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, document)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        selectBase64String = null
                        selectProfID = null
                    }
                    else{
                        uploadImageFromDevice.pickImage(this@ProfileActivity,
                            object : OnPiclImageToBase64 {
                                override fun onBase64(image64: String?) {
                                    selectProfID = this@ProfileActivity.resources.getStringArray(R.array.RegistrationDocument)[position]
                                    selectBase64String =image64
                                }
                            })
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun genderSpinnerData() {
        val gender = resources.getStringArray(R.array.Gender)

        val spinner = findViewById<Spinner>(R.id.sp_gender)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        selectGender=null
                    }
                    else{
                        selectGender = this@ProfileActivity.resources.getStringArray(R.array.Gender)[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}