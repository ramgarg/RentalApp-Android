package com.rental.merchant.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.rental.R
import com.rental.ValidationMessage
import com.rental.appbiz.AppBizLogger
import com.rental.common.model.modelclass.*
import com.rental.common.view.BaseActivity
import com.rental.common.viewmodel.MasterDataViewModel
import com.rental.common.viewmodel.ProductCategoriesViewModel
import com.rental.common.viewmodel.ProductSubCategoriesViewModel
import com.rental.customer.dashboard.view.adapter.InfalterViewAdapter
import com.rental.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.merchant_add_vehicle.*
import kotlinx.android.synthetic.main.toolbar.*

class MerchantAddVehicle : BaseActivity(),AdapterView.OnItemSelectedListener,InfalterViewAdapter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.merchant_add_vehicle)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.vehicle))

        getMasterCategory()

    }
    /*
    * to move other activity or componant
    * */
    override fun <T> moveOnSelecetedItem(type: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"move item")
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
         if(data is ProductCategoriesResModel){
            setSpinnerAdapter(data.vehicles,R.id.sp_select_category)
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
                    AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,masterResModelItem.name)
                    getProductByMasterCateName(masterResModelItem.name)
                }
                R.id.sp_select_category ->{
                    val vehicle = parent?.getItemAtPosition(position) as Vehicle
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
            rec_add_veichle.adapter = ProductVehiclesAdapter(list, this@MerchantAddVehicle,this)
        /*}else{
            rec_add_veichle.adapter!!.notifyDataSetChanged()
        }*/

    }

    override fun getInflaterViewIDAdapter(): Int {
        return R.layout.row_merchant_add_vehicle
    }


}