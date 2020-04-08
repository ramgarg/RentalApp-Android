package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import com.rental.Constant
import com.rental.ValidationMessage
import com.rental.appbiz.AppBizLogger
import com.rental.common.model.modelclass.ProductSubCategoriesResModel
import com.rental.common.model.modelclass.Vehicle
import com.rental.common.viewmodel.ProductSubCategoriesViewModel
import com.rental.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_home.*


class ProductSubCategoryActivity :ProductBaseActitvity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val masterResModelItem = intent.getParcelableExtra<Vehicle>(Constant.VEHICLES_DATA_ITEM)

        val selectedString = masterResModelItem.category_name
        AppBizLogger.log(AppBizLogger.LoggingType.INFO,masterResModelItem.toString())

//        val liveData = callAPI<ProductSubCategoriesViewModel>().getProductSubCate(selectedString)

        observeApiResult(callAPI<ProductSubCategoriesViewModel>().getProductSubCate(selectedString))
    }

    override fun <T> onSuccessApiResult(data: T) {

        data?.let {  if (data is ProductSubCategoriesResModel)
        {
            rec_veichle.adapter= ProductVehiclesAdapter(data,this@ProductSubCategoryActivity)
            search(data)
        }
            return
        }
        showToast(ValidationMessage.NO_DATA_FOUND)

    }


    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.openActivityWithParcelableParam<ProductDetailsActivity,T>(this,Constant.VEHICLES_SUB_CATE,type)
    }




}


