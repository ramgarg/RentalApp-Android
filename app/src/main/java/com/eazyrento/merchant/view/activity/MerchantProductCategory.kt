package com.eazyrento.merchant.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.view.adapter.InfalterViewAdapter
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.merchant.model.modelclass.MerchantProductItem
import com.eazyrento.merchant.view.fragment.MerchantCatItem
import com.eazyrento.merchant.viewModel.MerchantDeleteProductViewModel
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_merchant_all_product_home.*
import kotlinx.android.synthetic.main.toolbar.*

class MerchantProductCategory :BaseActivity(), InfalterViewAdapter {

    private  var deletePosition:Int = 0
    private lateinit var listMerchantCatItem:ArrayList<MerchantProductItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_all_product_home)

        topBarWithBackIconAndTitle(getString(R.string.view_product))


        val merchantCatItem = intent.getParcelableExtra<MerchantCatItem>(
            Constant.INTENT_MERCHANT_PRODUCT_LIST

        )

        listMerchantCatItem = merchantCatItem.value as ArrayList

        listMerchantCatItem?.let {
            rec_all_pro_home.adapter =
                ProductVehiclesAdapter(
                    it,
                    this,
                    this
                )

        }

    }



    override fun <T> moveOnSelecetedItem(type: T) {

    }
    override fun getInflaterViewIDAdapter(): Int {
        return R.layout.row_merchant_home_vehicle
    }

    override fun <T> setListnerOnView(
        view: View?,
        type: T,
        where: Int,
        pos: Int
    ) {

        view?.setOnClickListener{
            onViewEditDeleteClick(type,where,pos)
        }
    }


     fun <T, K> onViewEditDeleteClick(type: T, where: K,pos: Int) {

         deletePosition = pos

        when(where){
            Constant.edit -> MoveToAnotherComponent.moveToActivityWithIntentValue<AddProductDailogActivity>(this,
                Constant.INTENT_MERCHANT_PRODUCT_EDIT,(type as MerchantProductItem).id)

            Constant.delete->deleteProduct(type)

        }

    }

    private fun <T> deleteProduct(type: T) {
        val obj = type as MerchantProductItem
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<MerchantDeleteProductViewModel>(this)
                    .deletePoductAPI(obj.id)
                , this, this
            )


        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        if (data is JsonElement){
            listMerchantCatItem.removeAt(deletePosition)
            rec_all_pro_home.adapter?.notifyDataSetChanged()
            showToast(R.string.PRODUCT_DELETE_SUCCESS)

            if (listMerchantCatItem.size==0){
                showToast(R.string.NO_DATA_FOUND)
            }

        }
    }

    override fun onBackPressed() {
        finishCurrentActivityWithResult(Constant.MERCHANT_HOME_FRAGMENT_REQUEST_CODE,Intent())
        super.onBackPressed()
    }


}