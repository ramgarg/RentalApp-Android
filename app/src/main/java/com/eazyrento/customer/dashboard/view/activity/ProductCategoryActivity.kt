package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.MasterResModelItem
import com.eazyrento.common.model.modelclass.ProductCateItem
import com.eazyrento.common.viewmodel.ProductCategoriesViewModel
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.supporting.MyJsonParser
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.fragment_home.*


class ProductCategoryActivity : ProductBaseActitvity() {

    private var selectedKey:String=""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val masterResModelItem =
            intent.getParcelableExtra<MasterResModelItem>(
                Constant.MASTER_DATA_ITEM)

        selectedKey = masterResModelItem.name

        setData(selectedKey)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductCategoriesViewModel>(this)
                    .getProductCateg(selectedKey)
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
            AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"onsucess---"+data.toString())

             val list:List<ProductCateItem>? = MyJsonParser.convertJSONListIntoList(MyJsonParser.JsonArrayFromJsonObject((data as JsonElement).asJsonObject,selectedKey))

                list?.let {
                    rec_veichle.adapter=
                        ProductVehiclesAdapter(
                            it,
                            this@ProductCategoryActivity,
                            this
                        )
                    //serching items
                    setSearch(it as ArrayList<ProductCateItem>)

                    return
                }
                showToast(R.string.NO_DATA_FOUND)
            return
        }
        showToast(R.string.NO_DATA_FOUND)
    }

    override fun <T> setListnerOnView(
        view: View?,
        type: T,
        where: Int,
        pos: Int
    ) {
    }


}




