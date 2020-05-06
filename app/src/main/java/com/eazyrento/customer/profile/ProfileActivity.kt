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
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModelItem
import com.eazyrento.customer.myaddress.view.MyAddressListActivity
import com.eazyrento.customer.myaddress.view.adapter.MyAddressAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.login.model.modelclass.UserProfile
import com.eazyrento.login.viewmodel.UpdateProfileUserViewModel
import com.eazyrento.supporting.OnPiclImageToBase64
import com.eazyrento.supporting.UploadImageFromDevice
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity : BaseActivity() {
    var userProfile: UserProfile? = null

    private val uploadImageFromDevice = UploadImageFromDevice()
    private var selectProfID:String?=null
    private var selectBase64String:String?=null


    override fun <T> moveOnSelecetedItem(type: T) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        topBarWithBackIconAndTitle("Profile")

        userProfile = EazyRantoApplication.profileData

        documentSpinnerData()

        btn_save.setOnClickListener { onClickSaveButton() }

        btn_select_location.setOnClickListener {
            MoveToAnotherComponent.startActivityForResult<MyAddressListActivity>(this,Constant.ADDRESS_REQUECT_CODE,Constant.INTENT_ADDR_LIST,userProfile?.address_info!!.id)

        }

    }

    fun onClickSaveButton() {

        updateProfileData()
        userProfile?.let {
            updateProfileUser(it)
        }
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

            selectBase64String?.let {inner ->
                it.attached_document = inner
                it.id_proof_title = selectProfID!!
            }

        }
    }

    /*
    * update profile
    * */
    fun updateProfileUser(userProfile: UserProfile) {

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
}