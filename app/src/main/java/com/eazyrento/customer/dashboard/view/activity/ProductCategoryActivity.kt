package com.eazyrento.customer.dashboard.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.eazyrento.Constant
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.MasterResModelItem
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.common.model.modelclass.Vehicle
import com.eazyrento.common.view.ApiResult
import com.eazyrento.common.view.LiveDataActivityClass
import com.eazyrento.common.viewmodel.ProductCategoriesViewModel
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_home.*


class ProductCategoryActivity :
    ProductBaseActitvity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val masterResModelItem =
            intent.getParcelableExtra<MasterResModelItem>(
                Constant.MASTER_DATA_ITEM)
        AppBizLogger.log(AppBizLogger.LoggingType.INFO, masterResModelItem.toString())
        val selectedString = masterResModelItem.name

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductCategoriesViewModel>(this)
                    .getProductCateg(selectedString)
                , this, this
            )

        }
    }

   /* override fun <T> onSuccessApiResult(data: T) {

    }*/

    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.openActivityWithParcelableParam<ProductSubCategoryActivity,T>(this,
            Constant.VEHICLES_DATA_ITEM,type)
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            if (it is ProductCategoriesResModel)
            {
                it.vehicles?.let {
                    rec_veichle.adapter=
                        ProductVehiclesAdapter(
                            it,
                            this@ProductCategoryActivity,
                            this
                        )
                    search(it as ArrayList<Vehicle>)
                    return
                }
                showToast(ValidationMessage.NO_DATA_FOUND)
            }
            return
        }
        showToast(ValidationMessage.NO_DATA_FOUND)
    }
    }




