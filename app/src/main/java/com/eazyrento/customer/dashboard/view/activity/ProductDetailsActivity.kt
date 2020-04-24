package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductSubCategoriesModelResItem
import com.eazyrento.common.view.ApiResult
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.viewmodel.ProductDetailsViewModel
import com.eazyrento.customer.dashboard.model.modelclass.WishListModel
import com.eazyrento.customer.utils.Common.Companion.wishListModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_view_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsActivity : BaseActivity(),
    ApiResult {
    private var likeUnlike:Boolean=true
    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_details)

        val ResModelItem = intent.getParcelableExtra<ProductSubCategoriesModelResItem>(
            Constant.VEHICLES_SUB_CATE)

        val id = ResModelItem.id
        AppBizLogger.log(AppBizLogger.LoggingType.INFO,ResModelItem.toString())

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.details))

       setClick()

     // view model calling
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductDetailsViewModel>(this)
                    .getProductDetails(id)
                , this, this
            )
        }


    }

    private fun setClick() {

        btn_next.setOnClickListener {
            MoveToAnotherComponent.moveToBookingDetailsActivity(this)
        }
        img_like_unlike.setOnClickListener {
            if(likeUnlike) {
                likeUnlike=false
                //Here wishlist add data api will be called
                wishListModel.add(
                    WishListModel(
                        "",
                        "Volvo VH Truck",
                        "15",
                        1,
                        5
                    )
                )
                img_like_unlike.setImageResource(R.mipmap.like)
            }
            else {
                likeUnlike=true
                img_like_unlike.setImageResource(R.mipmap.unlike)
            }
        }
    }

    override fun <T> onSuccessApiResult(data: T) {

    }


}