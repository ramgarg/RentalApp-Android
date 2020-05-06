package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.*
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.viewmodel.MasterDataViewModel
import com.eazyrento.common.viewmodel.ProductCategoriesViewModel
import com.eazyrento.common.viewmodel.ProductSubCategoriesViewModel
import com.eazyrento.customer.dashboard.view.adapter.InfalterViewAdapter
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.supporting.MyJsonParser
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.merchant_add_vehicle.*
import kotlinx.android.synthetic.main.toolbar.*

class MerchantAddVehicleActivity : BaseActivity(),AdapterView.OnItemSelectedListener,
    InfalterViewAdapter {

    private var selectMasterCatName:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.merchant_add_vehicle)

        topBarWithBackIconAndTitle(getString(R.string.add_product))

        getMasterCategory()

    }
    /*
    * to move other activity or componant
    * */
    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.moveToActivityWithIntentValue<AddProductDailogActivity>(this,
            Constant.INTENT_MERCHANT_PRODUCT_ADD,(type as ProductSubCategoriesModelResItem).id)
    }

    override fun <T> onSuccessApiResult(data: T) {
        if(data==null){
            showToast(ValidationMessage.NO_DATA_FOUND)
            return
        }

        if (data is MasterResModel){
            setSpinnerAdapter(data,R.id.sp_select_type)
            return
        }
         if(data is JsonElement){
             val list:List<ProductCateItem>? = MyJsonParser.convertJSONListIntoList(MyJsonParser.JsonArrayFromJsonObject(data.asJsonObject,selectMasterCatName))
                setSpinnerAdapter(list,R.id.sp_select_category)
             return
        }
        else if(data is ProductSubCategoriesResModel){
            setSpinnerAdapter(data,R.id.sp_select_subcategory)
             return
        }

    }
/* spinner set adapter*/
    fun setSpinnerAdapter(list: List<*>?, id: Int){
        if (list==null)
        {
            showToast(ValidationMessage.NO_DATA_FOUND)
            return
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        val spinnerSelectType = findViewById<Spinner>(id)
        spinnerSelectType.adapter = adapter
        spinnerSelectType.onItemSelectedListener = this

//        recycler in bottom add
      setRecyclerAdapter(list)
    }

    /*Spinner selection*/

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,""+position)

            when(parent?.id)
            {
                R.id.sp_select_type ->{
                   val masterResModelItem = parent?.getItemAtPosition(position) as MasterResModelItem

                    selectMasterCatName = masterResModelItem.name
                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,selectMasterCatName)
                    getProductByMasterCateName(selectMasterCatName)
                }
                R.id.sp_select_category ->{
                    val vehicle = parent?.getItemAtPosition(position) as ProductCateItem
                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,""+vehicle.category_name)
                    getProSubcategoryByProName(vehicle.category_name)
                }
                R.id.sp_select_subcategory ->{
                    val subCategories = parent?.getItemAtPosition(position) as ProductSubCategoriesModelResItem
                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,""+subCategories.subcategory_name)

                }
            }

    }

    /*
    *
    * master categorires
    * */

    private fun getMasterCategory() {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MasterDataViewModel>(this).getMasterData()
                , this, this
            )
        }
    }

/*
* product name list
*
* */
    private fun getProductByMasterCateName(name:String) {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductCategoriesViewModel>(this).getProductCateg(name)
                , this, this
            )
        }
    }
/*
* product subcategory name
*
* */
    private fun getProSubcategoryByProName(name: String) {
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductSubCategoriesViewModel>(this).getProductSubCate(name)
                , this, this
            )
        }
    }

    /*
    *
    * set recycler adaper
    * */
  private fun <T>setRecyclerAdapter(list: List<T>){

        //if(rec_add_veichle.adapter==null) {
            rec_add_veichle.adapter =
                ProductVehiclesAdapter(
                    list,
                    this@MerchantAddVehicleActivity,
                    this
                )
        /*}else{
            rec_add_veichle.adapter!!.notifyDataSetChanged()
        }*/

    }

    override fun getInflaterViewIDAdapter(): Int {
        return R.layout.row_merchant_add_vehicle
    }

    override fun <T> setListnerOnView(
        view: View?,
        type: T,
        where: Int,
        pos: Int
    ) {
    }


}