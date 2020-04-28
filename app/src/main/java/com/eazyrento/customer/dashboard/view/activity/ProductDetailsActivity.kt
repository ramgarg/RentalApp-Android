package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.DynamicKeyValue
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.modelclass.ProductID
import com.eazyrento.common.model.modelclass.ProductSubCategoriesModelResItem
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.viewmodel.ProductDetailsViewModel
import com.eazyrento.customer.dashboard.view.adapter.AdapterProductDetails
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishAddViewModel
import com.eazyrento.customer.dashboard.viewmodel.CustomerWishDeleteViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import com.eazyrento.supporting.MyJsonParser
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsActivity : BaseActivity()
     {
          lateinit var dataProductDetails: ProductDetailsResModel
          var isAddOrDeleteWish:Boolean = false

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_details)

        val id = intent.getIntExtra(Constant.VEHICLES_SUB_CATE,-1)


        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.details))

     //Product details API calling
        isAddOrDeleteWish = false

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<ProductDetailsViewModel>(this)
                    .getProductDetails(id)
                , this, this
            )
        }


    }

    private fun setClick() {

        /*img_like_unlike.setOnClickListener {
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
        }*/
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        if (isAddOrDeleteWish){
            isAddOrDeleteWish = false
            return
        }

        if (data is JsonElement){

            isAddOrDeleteWish = false

            val dynamicJsonObj = data.asJsonObject.getAsJsonObject("product_details")

            //set adapter on recycle view
            setProductDetailsAdaptet(MyJsonParser.dynamicKeyValueList(dynamicJsonObj))
            // set data main UI
            dataProductDetails = MyJsonParser.convertJsonObjectToObject(data.asJsonObject)
            onsetDataUI(dataProductDetails)
        }

    }

    private fun onsetDataUI(data: ProductDetailsResModel) {

            pro_booking_price.text =Constant.DOLLAR_NOTATION+data.base_price
            pro_name.text = data.name
            setWishProductImage(data)
//            pro_wish.setImageResource(if(data.is_wishlisted) R.mipmap.like else R.mipmap.unlike)
            setProductImage(data.product_image_url)
    }

    private fun setProductDetailsAdaptet(data: List<DynamicKeyValue>) {

        rec_pro_details.adapter = AdapterProductDetails(this,data)

    }

    private fun setProductImage(productImageUrl: String) {
      Picasso.with(this).load(productImageUrl).into(pro_image)
    }

    fun onNextButtonClick(view:View){
        MoveToAnotherComponent.moveToBookingDetailsActivity(this)
    }

    fun onWishIconClick(view: View){

        isAddOrDeleteWish = true

        dataProductDetails.is_wishlisted = !dataProductDetails.is_wishlisted

        if (dataProductDetails.is_wishlisted) {
            //add wish
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<CustomerWishAddViewModel>(this)
                        .wishAdd(ProductID(dataProductDetails.id))
                    , this, this
                )
            }
        }
        else {
            //delete wish
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<CustomerWishDeleteViewModel>(this)
                        .wishDelete(dataProductDetails.id)
                    , this, this
                )
            }
        }

        setWishProductImage(dataProductDetails)
    }
    fun setWishProductImage(data: ProductDetailsResModel) {
        pro_wish.setImageResource(if(data.is_wishlisted) R.mipmap.like else R.mipmap.unlike)
    }

}



