package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductSubCategoriesResModel
import com.eazyrento.common.model.modelclass.ProductCateItem
import com.eazyrento.common.model.modelclass.ProductSubCategoriesModelResItem
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

        val masterResModelItem = intent.getParcelableExtra<ProductCateItem>(
            Constant.VEHICLES_DATA_ITEM)

        val selectedString = masterResModelItem.category_name

        setData(selectedString)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductSubCategoriesViewModel>(this)
                    .getProductSubCate(selectedString)
                , this, this
            )

        }
    }

    override fun <T> moveOnSelecetedItem(type: T) {
        MoveToAnotherComponent.moveToActivityWithIntentValue<ProductDetailsActivity>(this,
            Constant.VEHICLES_SUB_CATE,(type as ProductSubCategoriesModelResItem).id
        )
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
            setSearch(data)
        }
            return
        }
        showToast(ValidationMessage.NO_DATA_FOUND)
    }

    override fun <T> setListnerOnView(
        view: View?,
        type: T,
        where: Int,
        pos: Int
    ) {
    }

}


