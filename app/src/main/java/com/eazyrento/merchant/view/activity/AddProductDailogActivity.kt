package com.eazyrento.merchant.view.activity

import android.R.attr.tag
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.viewModel.MerchantAddProductViewModel
import kotlinx.android.synthetic.main.add_vehicle_dialog.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class AddProductDailogActivity:BaseActivity() {

    val merchantAddProductReqModel = MerchantAddProductReqModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle_dialog)
        this.setFinishOnTouchOutside(false)

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
//final submit
    fun onSubmitClick(view: View){

        merchantAddProductReqModel.price = ed_price.text.toString().toInt()

    callAPI()?.let {
        it.observeApiResult(
            it.callAPIActivity<MerchantAddProductViewModel>(this)
                .addProductAPI(merchantAddProductReqModel)
            , this, this
        )

     }
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }


    private fun setSppinerData(spinerDataReSource:Int,spinerName:Int) {
//        R.array.RegistrationDocument
        //R.id.sp_select_document
        val data = resources.getStringArray(spinerDataReSource)
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
                //Toast.makeText(this@AddProductDailogActivity, getString(R.string.selected_item) + " " + "" + document[position], Toast.LENGTH_SHORT).show()
                merchantAddProductReqModel.document_name = "pan"
//                merchantAddProductReqModel.
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
            /*val inputStream: InputStream? =
                data.data?.let { context?.getContentResolver()?.openInputStream(it) }*/

            try {
                // get uri from Intent
                val uri: Uri? = data.data
                // get bitmap from uri
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                // store bitmap to file
                val filename =
                    File(Environment.getExternalStorageDirectory(), "imageName.jpg")
                val out = FileOutputStream(filename)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out)
                out.flush()
                out.close()
                // get base64 string from file
                val base64: String? = getStringImage(filename)
                if (base64 != null) {
                    merchantAddProductReqModel.attach_document =base64
                }
                // use base64 for your next step.
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
    private fun getStringImage(file: File): String? {
        try {
            val fin = FileInputStream(file)
            val imageBytes = ByteArray(file.length().toInt())
            fin.read(imageBytes, 0, imageBytes.size)
            fin.close()
            return Base64.encodeToString(imageBytes, Base64.DEFAULT)
        } catch (ex: Exception) {
//            Log.e(tag, Log.getStackTraceString(ex))
//            toast("Image Size is Too High to upload.")
        }
        return null
    }

}