package com.eazyrento.merchant.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import com.eazyrento.Constant
import com.eazyrento.EazyRantoApplication.Companion.context
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductSubCategoriesModelResItem
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantProductItem
import com.eazyrento.merchant.viewModel.MerchantAddProductViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.add_vehicle_dialog.*
import java.io.ByteArrayOutputStream
import java.io.InputStream


class AddProductDailogActivity:BaseActivity() {

    private val merchantAddProductReqModel = MerchantAddProductReqModel()
    private  val DEFUALT_VALUE = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle_dialog)
        this.setFinishOnTouchOutside(false)

        //edit functionalty
        val edit_product = intent.getIntExtra(Constant.INTENT_MERCHANT_PRODUCT_EDIT,DEFUALT_VALUE)

        merchantAddProductReqModel.product_id =
            if (edit_product!=DEFUALT_VALUE)
                edit_product
        //Adding funtionalty
            else
               intent.getIntExtra(Constant.INTENT_MERCHANT_PRODUCT_ADD,DEFUALT_VALUE)


        setSppinerData( R.array.RegistrationDocument,R.id.sp_select_document)
        setSppinerData( R.array.FuelType,R.id.spinner_fuel_type)
        setCheckBoxListener()


    }

    private fun setCheckBoxListener() {
        var day:Int
        findViewById<CheckBox>(R.id.chk_box_with_driver).setOnCheckedChangeListener { buttonView, isChecked ->
            merchantAddProductReqModel.with_driver =isChecked
             }
        //days chk
        findViewById<CheckBox>(R.id.sun_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.sun =day
        }
        findViewById<CheckBox>(R.id.mon_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.mon =day
        }
        findViewById<CheckBox>(R.id.tue_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.tue =day
        }
        findViewById<CheckBox>(R.id.wed_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.wed =day
        }
        findViewById<CheckBox>(R.id.thu_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.thu =day
        }
        findViewById<CheckBox>(R.id.fri_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.fri =day
        }
        findViewById<CheckBox>(R.id.sat_chk).setOnCheckedChangeListener { buttonView, isChecked ->
            day = if (isChecked) 1 else 0
            merchantAddProductReqModel.availability_days.sat = day
        }
    }


    fun onMinusClick(view: View){
        merchantAddProductReqModel.quantity--
        item_quantity.text =""+merchantAddProductReqModel.quantity

    }
    fun onPlusClick(view: View){
        merchantAddProductReqModel.quantity++
        item_quantity.text =""+merchantAddProductReqModel.quantity

    }
    fun onUploadClick(view: View){
        pickImage()
    }
//final submit review
    fun onSubmitClick(view: View){

     if (isRequireValidationFailed(merchantAddProductReqModel)){
         return
     }
     showDialog(ValidationMessage.SUBMIT_TITLE,ValidationMessage.ON_SUBMIT_BUTTON_CLICK,this,R.layout.thank_you_pop)

    }
   private fun submitCallAPI(){
        merchantAddProductReqModel.price = ed_price.text.toString().toDouble()

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantAddProductViewModel>(this)
                    .addProductAPI(merchantAddProductReqModel)
                , this, this
            )

        }
    }

    override fun onClickDailog(int: Int) {
        when (int){
            Constant.OK -> submitCallAPI()
            Constant.CANCEL->AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,""+int)
        }

    }

    private fun isRequireValidationFailed(obj: MerchantAddProductReqModel): Boolean {

        if (!listOf<Int>(obj.availability_days.sun,obj.availability_days.mon,obj.availability_days.tue
            ,obj.availability_days.wed,obj.availability_days.thu,obj.availability_days.fri,obj.availability_days.sat).any(){it==1}) {

            showToast(ValidationMessage.SELECT_AT_LIST_ONE_DAYS)
            return true

        }
        if (obj.quantity==0)
        {
            showToast(ValidationMessage.FILL_QUANTITY)
            return true
        }
        if (ed_price.text.toString().isEmpty() && ed_price.text.toString().toDouble()==0.0){
            showToast(ValidationMessage.FILL_BOOKING_PRICE)
            return true
        }
        if (obj.variant.equals("")){
            showToast(ValidationMessage.SELECT_FUEL_TYPE_SPINNER)
            return true
        }
        if (obj.document_name.equals("")){
            showToast(ValidationMessage.SELECT_DOCUMENT)
            return true
        }
        if (obj.attach_document.equals("")){
            showToast(ValidationMessage.UPLOAD_DOCUMENT)
            return true
        }

        return false
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    fun getSpinnerDataByID(int: Int): Array<String> {
        return resources.getStringArray(int)
    }

    private fun setSppinerData(spinerDataReSource:Int,spinerName:Int) {
//        R.array.RegistrationDocument
        //R.id.sp_select_document
        val data = getSpinnerDataByID(spinerDataReSource)
        val spinner = findViewById<Spinner>(spinerName)

        spinner?.let {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
            it.adapter = adapter

            it.onItemSelectedListener = spinnerListnere
        }
    }

    val spinnerListnere = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if(position==0){

            }
            else{
                when(parent.id){
                    R.id.spinner_fuel_type ->{
                        merchantAddProductReqModel.variant = getSpinnerDataByID(R.array.FuelType)[position]
                    }
                    R.id.sp_select_document ->{
                        merchantAddProductReqModel.document_name = getSpinnerDataByID(R.array.RegistrationDocument)[position]
                    }
                }

            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // write code to perform some action
        }
    }

    //pic image
    open fun pickImage(): Unit {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, Constant.PICK_PHOTO_FOR_AVATAR)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constant.PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return
            }
            val imageStream: InputStream? =
                data.data?.let { context?.getContentResolver()?.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val base64 = encodeImage(selectedImage)
            if (base64 != null) {
                merchantAddProductReqModel.attach_document = base64
//                merchantAddProductReqModel.attach_document = ""
            }else{
                showToast(ValidationMessage.DOC_IS_NOT_UPLOADED)
            }

        }
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    override fun <T> onSuccessApiResult(data: T) {
        if (data is JsonElement) {
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, data.toString())
            showToast(ValidationMessage.PRODUCT_ADDED_SUCCESS)
//        {"status":201}
            MoveToAnotherComponent.moveToActivity<MerchantMainActivity>(
                this,
                Constant.INTENT_SUCCESS_ADDED_PRODUCT, 1
            )
        }

    }


}