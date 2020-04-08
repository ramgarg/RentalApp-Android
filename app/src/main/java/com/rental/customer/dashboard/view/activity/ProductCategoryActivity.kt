package com.rental.customer.dashboard.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.rental.Constant
import com.rental.ValidationMessage
import com.rental.appbiz.AppBizLogger
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.Vehicle
import com.rental.common.view.ApiResult
import com.rental.common.view.LiveDataActivityClass
import com.rental.common.viewmodel.ProductCategoriesViewModel
import com.rental.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.rental.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.fragment_home.*


class ProductCategoryActivity :ProductBaseActitvity(), ApiResult {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val masterResModelItem =
            intent.getParcelableExtra<MasterResModelItem>(Constant.MASTER_DATA_ITEM)
        AppBizLogger.log(AppBizLogger.LoggingType.INFO, masterResModelItem.toString())
        val selectedString = masterResModelItem.name

        LiveDataActivityClass(this).let {
            it.observeApiResult<ProductCategoriesResModel, LifecycleOwner, Context>(
                it.callAPIActivity<ProductCategoriesViewModel, FragmentActivity>(this)
                    .getProductCateg(selectedString)
                , this, this
            )

        }
    }

   /* override fun <T> onSuccessApiResult(data: T) {

    }*/

    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.openActivityWithParcelableParam<ProductSubCategoryActivity,T>(this,Constant.VEHICLES_DATA_ITEM,type)
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            if (it is ProductCategoriesResModel)
            {
                it.vehicles?.let {
                    rec_veichle.adapter= ProductVehiclesAdapter(it,this@ProductCategoryActivity)
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




