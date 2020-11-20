package com.eazyrento.merchant.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
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
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.merchant.viewModel.MerchantAddProductViewModel
import com.eazyrento.merchant.viewModel.MerchantProductDetailViewModel
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.add_vehicle_dialog.*
import java.io.ByteArrayOutputStream
import java.io.InputStream


class AddProductDailogActivity:BaseActivity() {

    private val merchantAddProductReqModel = MerchantAddProductReqModel()
    private  val DEFUALT_VALUE = -1
    var edit_product_ID:Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle_dialog)
        this.setFinishOnTouchOutside(false)

        item_quantity_static_text.text =getString(R.string.quantity)
        merchantAddProductReqModel.quantity =1

        //edit functionalty
         edit_product_ID = intent.getIntExtra(Constant.INTENT_MERCHANT_PRODUCT_EDIT,DEFUALT_VALUE)

        //
        merchantAddProductReqModel.product_id =
            if (edit_product_ID!=DEFUALT_VALUE)
                //editing functioalty
                editProductFuntionalty(edit_product_ID)

            else
            //Adding funtionalty
               intent.getIntExtra(Constant.INTENT_MERCHANT_PRODUCT_ADD,DEFUALT_VALUE)


        setSppinerData( R.array.RegistrationDocument,R.id.sp_select_document)
        setSppinerData( R.array.FuelType,R.id.spinner_fuel_type)

        setCheckBoxListener()


    }

    private fun editProductFuntionalty(editProductID:Int): Int {

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantProductDetailViewModel>(this)
                    .detailsPoductAPI(editProductID)
                , this, this
            )

        }

        return editProductID
    }

    private fun setCheckBoxListener() {
        var day:Int
        findViewById<CheckBox>(R.id.chk_box_with_driver).setOnCheckedChangeListener { buttonView, isChecked ->
            merchantAddProductReqModel.with_driver =isChecked
            ed_driver_price.visibility = View.VISIBLE
            tv_per_day_static.visibility = View.VISIBLE
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

    private fun setCheckBoxValue(merchantAddProductReqModel: MerchantAddProductReqModel){
        //drive chk
        findViewById<CheckBox>(R.id.chk_box_with_driver).isChecked =merchantAddProductReqModel.with_driver

        //days chk
            findViewById<CheckBox>(R.id.sun_chk).isChecked= merchantAddProductReqModel.availability_days.sun==1
            findViewById<CheckBox>(R.id.mon_chk).isChecked =merchantAddProductReqModel.availability_days.mon ==1
            findViewById<CheckBox>(R.id.tue_chk).isChecked= merchantAddProductReqModel.availability_days.tue ==1
            findViewById<CheckBox>(R.id.wed_chk).isChecked=merchantAddProductReqModel.availability_days.wed ==1
            findViewById<CheckBox>(R.id.thu_chk).isChecked=merchantAddProductReqModel.availability_days.thu ==1
            findViewById<CheckBox>(R.id.fri_chk).isChecked=merchantAddProductReqModel.availability_days.fri ==1
            findViewById<CheckBox>(R.id.sat_chk).isChecked =merchantAddProductReqModel.availability_days.sat ==1
    }


    fun onMinusClick(view: View){
        if (item_quantity.text.toString().toInt()-1 < 1){
            showToast(R.string.FILL_QUANTITY)
        }else {
            merchantAddProductReqModel.quantity--
            item_quantity.text = merchantAddProductReqModel.quantity.toString()
            item_quantity_static_text.text =getString(R.string.quantity)
        }

    }
    fun onPlusClick(view: View){
        merchantAddProductReqModel.quantity++
        item_quantity.text =merchantAddProductReqModel.quantity.toString()
        item_quantity_static_text.text =getString(R.string.quantity)

    }
    fun onUploadClick(view: View){
        pickImage()
    }
//final submit review
    fun onSubmitClick(view: View){

     if (isRequireValidationFailed(merchantAddProductReqModel)){
         return
     }
     showDialog(R.string.SUBMIT_TITLE,resources.getString(R.string.ON_SUBMIT_BUTTON_CLICK),this,R.layout.thank_you_pop)

    }
   private fun submitCallAPI(){
        merchantAddProductReqModel.price = ed_price.text.toString().toDouble()
       merchantAddProductReqModel.daily_price = ed_daily_price.text.toString().toDouble()
       merchantAddProductReqModel.monthly_price = ed_monthly_price.text.toString().toDouble()


       var boolean_add:Boolean = false
       var id:Int = edit_product_ID

       if (edit_product_ID ==DEFUALT_VALUE){
           // ADDED PROJECT
           boolean_add = true
           id =DEFUALT_VALUE
       }

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantAddProductViewModel>(this)
                    .addProductAPI(merchantAddProductReqModel,boolean_add,id)
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

            showToast(R.string.SELECT_AT_LIST_ONE_DAYS)
            return true

        }
        if (obj.quantity==0)
        {
            showToast(R.string.FILL_QUANTITY)
            return true
        }
        if (ed_price.text.isEmpty() || ed_price.text.toString().toDouble()==0.0){
            showToast(R.string.FILL_BOOKING_PRICE)
            return true
        }
        if (ed_daily_price.text.isEmpty() || ed_daily_price.text.toString().toDouble()==0.0){
            showToast(R.string.FILL_DAILY_PRICE)
            return true
        }
        if (ed_monthly_price.text.isEmpty() || ed_monthly_price.text.toString().toDouble()==0.0){
            showToast(R.string.FILL_MONTHLY_PRICE)
            return true
        }
        if(findViewById<CheckBox>(R.id.chk_box_with_driver).isChecked){
        if (ed_driver_price.text.isEmpty() || ed_driver_price.text.toString().toDouble()==0.0){
            showToast(R.string.FILL_DRIVER_PRICE)
            return true
        } else{
            merchantAddProductReqModel.driver_cost_per_day = ed_driver_price.text.toString().toDouble()
        }
        }
        if (obj.variant.equals("")){
            showToast(R.string.SELECT_FUEL_TYPE_SPINNER)
            return true
        }
        if (obj.document_name.equals("")){
            showToast(R.string.SELECT_DOCUMENT)
            return true
        }
        if (obj.attach_document.equals("")){
            showToast(R.string.UPLOAD_DOCUMENT)
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
            val adapter = ArrayAdapter(this, R.layout.spinner_item_style, data)
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

        // set Image in view and set base64 to send server
            setImageViewAndConvertIntoBase64(selectedImage)


        }
    }

    private fun setImageViewAndConvertIntoBase64(selectedImage: Bitmap?) {
        //set into Image view
        //img_doc.visibility = View.VISIBLE
        selectedImage?.let {
            img_doc.setImageBitmap(selectedImage)

            val base64 = encodeImage(selectedImage)
            if (base64 != null) {
                merchantAddProductReqModel.attach_document = base64
//                merchantAddProductReqModel.attach_document = ""
            }else{
                showToast(R.string.DOC_IS_NOT_UPLOADED)
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

                showToast(R.string.REQUEST_SUCCESSED)

                MoveToAnotherComponent.moveToActivityWithIntentValue<MerchantMainActivity>(
                    this,
                    Constant.INTENT_SUCCESS_ADDED_PRODUCT, 1
                )
        }
        else{
            editProductData(data as MerchantProductDetailsResModel)
        }

    }

    private fun editProductData(productDetails: MerchantProductDetailsResModel) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,productDetails.toString())

//        merchantAddProductReqModel.product_id =productDetails.product_info.id

        merchantAddProductReqModel.availability_days = productDetails.product_info.availability_days
        merchantAddProductReqModel.document_name =productDetails.product_info.document_name
        merchantAddProductReqModel.quantity = productDetails.product_info.quantity
        item_quantity_static_text.text =getString(R.string.quantity)
        merchantAddProductReqModel.price =productDetails.product_info.price
        merchantAddProductReqModel.variant = productDetails.product_info.product_details.fuel_type
        merchantAddProductReqModel.with_driver =productDetails.product_info.with_driver
        merchantAddProductReqModel.daily_price = productDetails.product_info.daily_price
        merchantAddProductReqModel.monthly_price = productDetails.product_info.monthly_price
        merchantAddProductReqModel.driver_cost_per_day = productDetails.product_info.driver_cost_per_day


        setAttachedDocOnImageView(productDetails.product_info.attached_document)

        setDataOnUI(merchantAddProductReqModel)

    }
    private fun setDataOnUI(merchantAddProductReqModel: MerchantAddProductReqModel){

        setCheckBoxValue(merchantAddProductReqModel)

        item_quantity.text = ""+merchantAddProductReqModel.quantity

        ed_price.setText(""+merchantAddProductReqModel.price)
        ed_daily_price.setText(""+merchantAddProductReqModel.daily_price)
        ed_monthly_price.setText(""+merchantAddProductReqModel.monthly_price)
        ed_driver_price.setText(""+merchantAddProductReqModel.driver_cost_per_day)

        setSpinnerData(merchantAddProductReqModel)


    }

    private fun setSpinnerData(merchantAddProductReqModel: MerchantAddProductReqModel) {
        spinner_fuel_type.setSelection(getComparedPostion(getSpinnerDataByID(R.array.FuelType),merchantAddProductReqModel.variant))
        sp_select_document.setSelection(getComparedPostion(getSpinnerDataByID(R.array.RegistrationDocument),merchantAddProductReqModel.document_name))
    }

    private fun getComparedPostion(spinnerDataByID: Array<String>, variant: String): Int {

        for (i in spinnerDataByID.indices){
            if (spinnerDataByID[i] == variant)
                return i
        }
        return 0
    }

    private fun setAttachedDocOnImageView(attachedDocument: String) {
        if (attachedDocument.isEmpty())
            return

        Picasso.with(context).load(attachedDocument).into(object :Target{
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                setImageViewAndConvertIntoBase64(bitmap)
            }

        })
    }

    fun onCloseButtonClick(view: View){
        finishCurrentActivity(0)
    }


}