package com.rental.customer.dashboard.view.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.rental.Constant
import com.rental.R
import com.rental.appbiz.AppBizLogger
import com.rental.common.model.modelclass.ProductDetailsResModel
import com.rental.common.model.modelclass.ProductSubCategoriesModelResItem
import com.rental.common.model.modelclass.ProductSubCategoriesResModel
import com.rental.common.view.ApiResult
import com.rental.common.view.BaseActivity
import com.rental.common.view.LiveDataActivityClass
import com.rental.common.viewmodel.ProductDetailsViewModel
import com.rental.common.viewmodel.ProductSubCategoriesViewModel
import com.rental.customer.dashboard.model.modelclass.WishListModel
import com.rental.customer.dashboard.viewmodel.CustomerHomeViewModel
import com.rental.customer.utils.Common.Companion.wishListModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_view_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsActivity :BaseActivity(),ApiResult {
    private lateinit var customerHomeViewModel:CustomerHomeViewModel
    private var likeUnlike:Boolean=true
    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_details)

        val ResModelItem = intent.getParcelableExtra<ProductSubCategoriesModelResItem>(Constant.VEHICLES_SUB_CATE)

        val id = ResModelItem.id
        AppBizLogger.log(AppBizLogger.LoggingType.INFO,ResModelItem.toString())

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.details))

       setClick()

     // view model calling
        LiveDataActivityClass(this).let {
            it.observeApiResult<ProductDetailsResModel, LifecycleOwner, Context>(
                it.callAPIActivity<ProductDetailsViewModel, FragmentActivity>(this)
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
                wishListModel.add(WishListModel("","Volvo VH Truck","15",1,5))
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