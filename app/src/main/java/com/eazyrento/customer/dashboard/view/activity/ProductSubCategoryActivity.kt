package com.eazyrento.customer.dashboard.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eazyrento.Constant
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductSubCategoriesResModel
import com.eazyrento.common.model.modelclass.Vehicle
import com.eazyrento.common.view.ApiResult
import com.eazyrento.common.viewmodel.ProductSubCategoriesViewModel
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_home.*


class ProductSubCategoryActivity :
    ProductBaseActitvity(),
    ApiResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val masterResModelItem = intent.getParcelableExtra<Vehicle>(
            Constant.VEHICLES_DATA_ITEM)

        val selectedString = masterResModelItem.category_name
        AppBizLogger.log(AppBizLogger.LoggingType.INFO,masterResModelItem.toString())

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductSubCategoriesViewModel>(this)
                    .getProductSubCate(selectedString)
                , this, this
            )

        }
    }

    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.openActivityWithParcelableParam<ProductDetailsActivity,T>(this,
            Constant.VEHICLES_SUB_CATE,type)
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {  if (data is ProductSubCategoriesResModel)
        {
            rec_veichle.adapter=
                ProductVehiclesAdapter(
                    data,
                    this@ProductSubCategoryActivity,
                    this
                )
            search(data)
        }
            return
        }
        showToast(ValidationMessage.NO_DATA_FOUND)
    }

}


