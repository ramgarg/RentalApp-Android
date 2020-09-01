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
import com.eazyrento.customer.myaddress.view.AddNewAddressActivity
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.Validator
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.UpdateProfileUserViewModel
import com.eazyrento.supporting.*
import com.squareup.picasso.Picasso
import io.michaelrocks.libphonenumber.android.Phonenumber
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.document_pic
import kotlinx.android.synthetic.main.activity_profile.ed_full_name
import kotlinx.android.synthetic.main.activity_profile.sp_select_document

open class UpdateProfileBase : BaseActivity() {
    protected var userProfile: UserProfile? = null

    private val uploadImageFromDevice = UploadImageFromDevice()

    protected var selectProfID: String? = null
    protected var selectBase64StringAttachedDoc: String? = null

    private var selectGender: String? = null

    private var selectBase64StringProfilePic: String? = null

    private var isEditableDocumentSpinner: Int = 0

    private lateinit var phoneNumberFormat: PhoneNumberFormat

    private val commonDatePiker = CommonDatePiker(this)

   /* private val isCustomer = Session.getInstance(EazyRantoApplication.context)?.getUserRole()
        .equals(UserInfoAPP.CUSTOMER)*/

   /* private val isAgent = Session.getInstance(EazyRantoApplication.context)?.getUserRole()
        .equals(UserInfoAPP.AGENT)*/

    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ed_full_name.filters = filterAs(FilterEnum.FULL_NAME)
        ed_company_name.filters = filterAs(FilterEnum.ALPHANUMERIC)
        ed_des.filters =  filterAs(FilterEnum.ALPHANUMERIC)

        topBarWithBackIconAndTitle(resources.getString(R.string.profile))

        /*if (isCustomer) {
            layout_dob.visibility = View.GONE
            lyt_select_document.visibility = View.GONE
        }*/
       /* if (isAgent){
            lyt_select_document.visibility = View.GONE
            layout_company.visibility = View.GONE
        }*/

        phoneNumberFormat = PhoneNumberFormat(this)

        //layout_phone.setEmptyDefault(resources.getString(R.string.iso_phone_number))

        userProfile = EazyRantoApplication.profileData

        documentSpinnerData()
        genderSpinnerData()

        userProfile?.let { setProfileData(it) }

        phoneNumberFormat.phoneNumberListener(this, ed_phone, ed_country)
        phoneNumberFormat.phoneCountryCodeNumberListener(ed_country)


        btn_save.setOnClickListener { onClickSaveButton() }

        /* layout_dob.setOnClickListener {

         }*/

        btn_select_location.setOnClickListener {

            if ((it as Button).text == (resources.getString(R.string.select_address))) {
                MoveToAnotherComponent.startActivityForResult<AddNewAddressActivity>(
                    this,
                    Constant.REQUEST_CODE_PROFILE_UPDATE,
                    Constant.KEY_FROM_PROFILE,
                    Constant.FIRST_TIME_USER_LOGIN
                )
                return@setOnClickListener
            }
            MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(
                this,
                Constant.ADDRESS_REQUECT_CODE,
                Constant.INTENT_ADDR_LIST,
                1
            )
        }

    }

    fun dobClick(view: View) {
        // Common.dateSelector(this,ed_dob)

//    val commonDatePiker = CommonDatePiker(this)

        commonDatePiker.createDatePicker(EnumDateType.DOB, object : OnSelectDate {
            override fun onDate(dateType: EnumDateType, year: Int, month: Int, day: Int) {

                ed_dob.tag = commonDatePiker.getDateInServerFormate(year, month, day)
                dobDate(year, month, day)
            }
        }).dobPiker().show()
    }

    private fun dobDate(year: Int, month: Int, day: Int) {

        ed_dob.setText(commonDatePiker.getDateInDisplayFormat(year, month - 1, day))
    }

    private fun setProfileData(userProfile: UserProfile) {

        tv_user_name_profile.text = Session.getInstance(this)?.getUserRole()?.capitalize()
        ed_full_name.setText(userProfile.full_name)
        // ed_user_name.setText(userProfile?.full_name)
        if (userProfile.email.isNullOrEmpty())
            ed_email.isEnabled = true
        else
            ed_email.setText(userProfile.email)


        try {
            if (userProfile.country_code.isNullOrEmpty())
                ed_country.setText("+".plus(phoneNumberFormat.getCountryCodeForLocalRegion()))
            else
                ed_country.setText(userProfile.country_code)

            if (userProfile.mobile_number.isNullOrEmpty() || userProfile.mobile_number.equals("unknown")) {
                AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"Mobile no is Empty")
            } else {
                /*val code = phoneNumberFormat.getRegionCodeForCountryCode(
                    phoneNumberFormat.removePlusChar(ed_country).toInt()
                )

                val parseNumber = if (code == null) {
                    phoneNumberFormat.parseNumberWithoutCountryCode(
                        userProfile.mobile_number
                    )
                } else {

                    phoneNumberFormat.parseNumberWithCountryCode(
                        userProfile.mobile_number,
                        code
                    )
                }*/
                val parseNumber = parseMobileNumber(userProfile.mobile_number)

                if (parseNumber == null)
                    ed_phone.setText(userProfile.mobile_number)
                else
                    ed_phone.setText("".plus(parseNumber.nationalNumber))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {

            ed_dob.tag = userProfile.dob

            val list = ed_dob.tag.toString().split("-")

            dobDate(list[0].toInt(), list[1].toInt(), list[2].toInt())


        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }


        ed_company_name.setText(userProfile.buisness)
        ed_des.setText(userProfile.description)

        setAddress()

        Picasso.with(this).load(userProfile.profile_image).into(img_profile)

        if (userProfile.attached_document.isNullOrEmpty().not())
            Picasso.with(this).load(userProfile.attached_document).into(document_pic)

        sp_gender.setSelection(
            getComparedPostion(
                getSpinnerDataByID(R.array.Gender),
                userProfile.gender
            )
        )
        isEditableDocumentSpinner = getComparedPostion(
            getSpinnerDataByID(R.array.RegistrationDocument),
            userProfile.id_proof_title
        )
        sp_select_document.setSelection(isEditableDocumentSpinner)

        img_edit.setOnClickListener {
            uploadImageFromDevice.pickImage(this, object : OnPiclImageToBase64 {
                override fun onBase64(string: String?) {
                    selectBase64StringProfilePic = string
                }

                override fun onBitmap(bm: Bitmap?) {
                    img_profile.setImageBitmap(bm)
                }
            })
        }


    }

    private fun parseMobileNumber(mobileNumber:String): Phonenumber.PhoneNumber? {
        val code = phoneNumberFormat.getRegionCodeForCountryCode(
            phoneNumberFormat.removePlusChar(ed_country).toInt()
        )

        val parseNumber = if (code == null) {
            phoneNumberFormat.parseNumberWithoutCountryCode(
                mobileNumber
            )
        } else {

            phoneNumberFormat.parseNumberWithCountryCode(
                mobileNumber,
                code
            )
        }

        return parseNumber
    }

    private fun getComparedPostion(spinnerDataByID: Array<String>, variant: String?): Int {

        for (i in spinnerDataByID.indices) {
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
            if (isProfileValidationCorrect()) {
                updateProfileUserAPI(it)
            }



        }
    }

    open protected fun isProfileValidationCorrect(): Boolean {
        when{

            ed_full_name.text.toString().isEmpty() || ed_full_name.text.toString().length< resources.getInteger(R.integer.full_name_min_len)->showToast(R.string.VALID_USER_NAME)
            ed_email.text.toString().isEmpty()||Validator.isEmailValid(ed_email.text.toString()).not() ->showToast(R.string.VALID_EMAIL_ID)

            phoneNumberFormat.isValidCountryCode(ed_country.text.toString()).not()->R.string.COUNTRY_CODE
            isValidPhoneNumber(""+parseMobileNumber(ed_phone.text.toString())?.nationalNumber,this).not()->showToast(R.string.PHONE_NUMBER)

//            ed_des.text.toString().isEmpty()->showToast(ValidationMessage.DESCRIPTON)
            sp_gender.selectedItemPosition == 0->showToast(R.string.GENDER)
            tv_add_line.text.isNullOrEmpty() -> showToast(R.string.SELECT_ADRESS)

//            isAgent.not() && ed_company_name.text.toString().isEmpty()->showToast(ValidationMessage.COMPANY)
//            isCustomer.not() && ed_dob.text.toString().isEmpty()-> showToast(ValidationMessage.DATE_OF_BIRTH)
//            isCustomer.not() && isAgent.not() && sp_select_document.selectedItemPosition == 0 ->showToast(ValidationMessage.DOCUMENT)

            else ->return true

        }
        return false
        /*else if (img_profile.drawable == null) {
            showToast(ValidationMessage.VALID_IMAGE)
        }*/
    }

    open protected fun updateProfileData() {

        userProfile?.let { it ->

            it.gender = "" + sp_gender.selectedItem
            it.email = "" + ed_email.text
            it.description = "" + ed_des.text

            it.country_code = ed_country.text.toString()
            it.mobile_number = ed_phone.text.toString()


            it.buisness = "" + ed_company_name.text


            it.id_proof_title = "" + sp_select_document.selectedItem

            it.profile_image = "" + img_profile

            // selectBase64StringProfilePic?.let {inner_base64->
            it.profile_image = selectBase64StringProfilePic
            //}

            // if not customer
           /* if (isCustomer) {
                it.dob = null
                it.attached_document = null
            }else */ /*if (isAgent){

                it.dob = "" + ed_dob.tag

                it.attached_document = null
                it.buisness = null

            }*/ /*else {
                it.dob = "" + ed_dob.tag

                it.attached_document = "" + selectBase64StringAttachedDoc

                selectBase64StringAttachedDoc?.let { inner ->
                    it.attached_document = inner
                    it.id_proof_title = selectProfID!!
                }
            }*/
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

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, data.toString())
        showToast(R.string.PROFILE_UPDATE)
        //first time user
        if (intent.getIntExtra(
                Constant.KEY_FINISH_FIRST_TIME_USER,
                0
            ) == Constant.VALUE_FINISH_FIRST_TIME_USER
        ) {
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
        } else if (resultCode == Activity.RESULT_OK && requestCode == Constant.ADDRESS_REQUECT_CODE) {

            userProfile?.let {

                val addressInfo = data!!.getParcelableExtra<AddressInfo>(Constant.KEY_ADDRESS)

                addressInfo?.let { inner ->

                    it.address_info = inner

                    if (intent.getIntExtra(
                            Constant.KEY_FINISH_FIRST_TIME_USER,
                            0
                        ) == Constant.VALUE_FINISH_FIRST_TIME_USER
                    ) {
                        it.address_info.is_default = true
                    }
                }

                setAddress()

            }

        } else if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_CODE_PROFILE_UPDATE) {

            userProfile?.let {

                val addressInfo = data!!.getParcelableExtra<AddressInfo>(Constant.KEY_FROM_PROFILE)

                addressInfo?.let { inner ->
                    it.address_info = inner
                }

                setAddress()

            }

        }
    }

    fun setAddress() {
        userProfile?.let {

            if (it.address_info == null) {
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
            val adapter = ArrayAdapter(this, R.layout.spinner_item_style, document)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        selectBase64StringAttachedDoc = null
                        selectProfID = null
                    } else {
                        // alreday selected the document spiiner
                        if (isEditableDocumentSpinner != 0) {
                            isEditableDocumentSpinner = 0
                            return
                        }
                        uploadImageFromDevice.pickImage(this@UpdateProfileBase,
                            object : OnPiclImageToBase64 {
                                override fun onBase64(image64: String?) {
                                    selectProfID =
                                        this@UpdateProfileBase.resources.getStringArray(R.array.RegistrationDocument)[position]
                                    selectBase64StringAttachedDoc = image64
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
            val adapter = ArrayAdapter(this, R.layout.spinner_item_style, gender)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        selectGender = null
                    } else {
                        selectGender =
                            this@UpdateProfileBase.resources.getStringArray(R.array.Gender)[position]
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