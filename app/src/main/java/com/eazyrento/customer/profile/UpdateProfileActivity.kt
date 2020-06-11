package com.eazyrento.customer.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.eazyrento.*
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.myaddress.view.AddNewAddressActivity
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Validator
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.UpdateProfileUserViewModel
import com.eazyrento.supporting.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class UpdateProfileActivity : BaseActivity() {
    var userProfile: UserProfile? = null

    private val uploadImageFromDevice = UploadImageFromDevice()

    private var selectProfID:String?=null
    private var selectBase64StringAttachedDoc:String?=null

    private var selectGender:String?=null

    private var selectBase64StringProfilePic:String?=null

    private var isEditableDocumentSpinner:Int =0

    private lateinit var phoneNumberFormat:PhoneNumberFormat

    private val commonDatePiker = CommonDatePiker(this)
    private val isCustomer = Session.getInstance(EazyRantoApplication.context)?.getUserRole().equals(UserInfoAPP.CUSTOMER)

    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        topBarWithBackIconAndTitle(resources.getString(R.string.profile))

        if (isCustomer){
            layout_dob.visibility = View.GONE
            lyt_select_document.visibility = View.GONE
        }

        phoneNumberFormat = PhoneNumberFormat(this)

        //layout_phone.setEmptyDefault(resources.getString(R.string.iso_phone_number))

        userProfile = EazyRantoApplication.profileData

        documentSpinnerData()
        genderSpinnerData()

        userProfile?.let { setProfileData(it) }

        phoneNumberFormat.phoneNumberListener(this,ed_phone,ed_country)
        phoneNumberFormat.phoneCountryCodeNumberListener(ed_country)


        btn_save.setOnClickListener { onClickSaveButton() }

       /* layout_dob.setOnClickListener {

        }*/

        btn_select_location.setOnClickListener {

            if ((it as Button).text==(resources.getString(R.string.select_address))){
                MoveToAnotherComponent.startActivityForResult<AddNewAddressActivity>(this,Constant.REQUEST_CODE_PROFILE_UPDATE,Constant.KEY_FROM_PROFILE,Constant.FIRST_TIME_USER_LOGIN)
                return@setOnClickListener
            }
            MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(this,Constant.ADDRESS_REQUECT_CODE,Constant.INTENT_ADDR_LIST,1)
        }

    }
  fun dobClick(view: View){
    // Common.dateSelector(this,ed_dob)

//    val commonDatePiker = CommonDatePiker(this)

    commonDatePiker.createDatePicker(EnumDateType.DOB, object : OnSelectDate {
        override fun onDate(dateType: EnumDateType, year: Int, month: Int, day: Int) {

            ed_dob.tag = commonDatePiker.getDateInServerFormate(year,month,day)
            dobDate(year,month,day)
        }
    }).dobPiker().show()
}

    private fun dobDate(year: Int, month: Int, day: Int) {

        ed_dob.setText(commonDatePiker.getDateInDisplayFormat(year,month-1,day))
    }
    private fun setProfileData(userProfile: UserProfile) {

        tv_user_name_profile.text = Session.getInstance(this)?.getUserRole()?.capitalize()
        ed_full_name.setText(userProfile.full_name)
       // ed_user_name.setText(userProfile?.full_name)
        if(userProfile.email.isNullOrEmpty())
            ed_email.isEnabled = true
        else
            ed_email.setText(userProfile.email)


        try {
            if (userProfile.country_code.isNullOrEmpty())
                ed_country.setText("+".plus(phoneNumberFormat.getCountryCodeForLocalRegion()))
            else
                ed_country.setText(userProfile.country_code)

            ed_phone.setText(userProfile.mobile_number)

        }catch (e:Exception){
            e.printStackTrace()
        }

        try {

            ed_dob.tag = userProfile.dob

            val list = ed_dob.tag.toString().split("-")

            dobDate(list[0].toInt(),list[1].toInt(),list[2].toInt())


        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }


        ed_company_name.setText(userProfile.buisness)
        ed_des.setText(userProfile.description)

        setAddress()

        Picasso.with(this).load(userProfile.profile_image).into(img_profile)
        Picasso.with(this).load(userProfile.attached_document).into(document_pic)

        sp_gender.setSelection(getComparedPostion(getSpinnerDataByID(R.array.Gender),userProfile.gender))
        isEditableDocumentSpinner = getComparedPostion(getSpinnerDataByID(R.array.RegistrationDocument),userProfile.id_proof_title)
        sp_select_document.setSelection(isEditableDocumentSpinner)

        img_edit.setOnClickListener {
            uploadImageFromDevice.pickImage(this,object :OnPiclImageToBase64{
                override fun onBase64(string: String?) {
                    selectBase64StringProfilePic = string
                }

                override fun onBitmap(bm: Bitmap?) {
                    img_profile.setImageBitmap(bm)
                }
            })
        }


    }

    private fun getComparedPostion(spinnerDataByID: Array<String>, variant: String?): Int {

        for (i in spinnerDataByID.indices){
            if (spinnerDataByID[i] == variant) {

                return i
            }
        }
        return 0
    }

    fun getSpinnerDataByID(int: Int): Array<String> {
        return resources.getStringArray(int)
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
            showToast(ValidationMessage.VALID_PROFILE_NAME)
        }
        else if (img_profile.drawable == null) {
            showToast(ValidationMessage.VALID_IMAGE)
        }
        else if(ed_email.text.toString().isEmpty()){
            showToast(ValidationMessage.VALID_EMAIL_ID)
        }
        else if (!Validator.isEmailValid(ed_email.text.toString())) {
            showToast(ValidationMessage.VALID_EMAIL_ID)
            ed_email.requestFocus()
        }

        else if(!phoneNumberFormat.isValidCountryCode(ed_country.text.toString())){
            showToast(ValidationMessage.COUNTRY_CODE)
        }
        else if(!phoneNumberFormat.isValidPhoneNumber(ed_phone.text.toString())){
            showToast(ValidationMessage.PHONE_NUMBER)
        }


        else if(ed_company_name.text.toString().isEmpty()){
            showToast(ValidationMessage.COMPANY)
        }
        else if(ed_des.text.toString().isEmpty()){
            showToast(ValidationMessage.DESCRIPTON)
        }
        else if(sp_gender.selectedItemPosition==0){
            showToast(ValidationMessage.GENDER)
        }

        else if (tv_add_line.text.isNullOrEmpty()){
            showToast(ValidationMessage.SELECT_ADRESS)

        }
        //not customer
        else if (isCustomer.not()) {
            if (ed_dob.text.toString().isEmpty()) {
                showToast(ValidationMessage.DATE_OF_BIRTH)
            } else if (sp_select_document.selectedItemPosition == 0) {
                showToast(ValidationMessage.DOCUMENT)
            }
            else {
                return true
            }
        }
        else{
            return true
        }
        return false
    }

    private fun updateProfileData() {

        userProfile?.let { it ->
            //it.full_name = "" + ed_user_name.text
            it.dob = "" + ed_dob.tag
            it.gender = "" + sp_gender.selectedItem
            it.email = "" + ed_email.text
            it.description = "" + ed_des.text

            it.country_code = "" + ed_country.text
            it.mobile_number = "" + ed_phone.text


            it.buisness = "" + ed_company_name.text
            it.attached_document = "" + selectBase64StringAttachedDoc

            it.id_proof_title = "" + sp_select_document.selectedItem

            it.profile_image = "" + img_profile
            //it.username_choice = "" + ed_user_name.text

           // selectBase64StringProfilePic?.let {inner_base64->
                it.profile_image = selectBase64StringProfilePic
            //}

            selectBase64StringAttachedDoc?.let { inner ->
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
        showToast(ValidationMessage.PROFILE_UPDATE)
        //first time user
        if (intent.getIntExtra(Constant.KEY_FINISH_FIRST_TIME_USER,0)==Constant.VALUE_FINISH_FIRST_TIME_USER){
            finishCurrentActivityWithResult(Activity.RESULT_OK, Intent())
            return
        }
        finishCurrentActivity(Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            uploadImageFromDevice.onActivityResult(requestCode, resultCode, data)
            return
        }

       else if (resultCode== Activity.RESULT_OK && requestCode ==Constant.ADDRESS_REQUECT_CODE){

            userProfile?.let {

                val addressInfo = data!!.getParcelableExtra<AddressInfo>(Constant.KEY_ADDRESS)

                addressInfo?.let {inner->

                    it.address_info =inner

                    if (intent.getIntExtra(Constant.KEY_FINISH_FIRST_TIME_USER,0)==Constant.VALUE_FINISH_FIRST_TIME_USER) {
                        it.address_info.is_default = true
                    }
                }

                setAddress()

            }

        }
       else if (resultCode== Activity.RESULT_OK && requestCode ==Constant.REQUEST_CODE_PROFILE_UPDATE){

            userProfile?.let {

                val addressInfo = data!!.getParcelableExtra<AddressInfo>(Constant.KEY_FROM_PROFILE)

                 addressInfo?.let {inner->
                     it.address_info =inner
                 }

                setAddress()

            }

        }
    }

    fun setAddress(){
        userProfile?.let {

            if(it.address_info==null){
                btn_select_location.text = resources.getString(R.string.select_address)
                return
            }

            tv_add_country.setText(it.address_info?.country)
            tv_add_city.setText(it.address_info?.city)
            tv_add_line.setText(it.address_info?.address_line)
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
                        selectBase64StringAttachedDoc = null
                        selectProfID = null
                    }
                    else{
                        // alreday selected the document spiiner
                        if( isEditableDocumentSpinner!=0){
                            isEditableDocumentSpinner =0
                            return
                        }
                        uploadImageFromDevice.pickImage(this@UpdateProfileActivity,
                            object : OnPiclImageToBase64 {
                                override fun onBase64(image64: String?) {
                                    selectProfID = this@UpdateProfileActivity.resources.getStringArray(R.array.RegistrationDocument)[position]
                                    selectBase64StringAttachedDoc =image64
                                }

                                override fun onBitmap(bm: Bitmap?) {
                                    document_pic.setImageBitmap(bm)
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
                        selectGender = this@UpdateProfileActivity.resources.getStringArray(R.array.Gender)[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    override fun <T> statusCodeOfApi(data: T) {

    }

}