package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.rental.Constant
import com.rental.R
import com.rental.ValidationMessage
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.Vehicle
import com.rental.common.view.BaseActivity
import com.rental.common.viewmodel.ProductCategoriesViewModel
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.adapter.ProductCategoriesAdapter
import com.rental.customer.dashboard.viewmodel.CustomerHomeViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.Common
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.category_activity.*
import kotlinx.android.synthetic.main.fragment_home.rec_veichle
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.math.roundToInt


class ProductCategoryActivity :BaseActivity() {

    private lateinit var arrayList:ArrayList<Vehicle>
     var arrayListSort:ArrayList<Vehicle> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       setContentView(R.layout.category_activity)

        val masterResModelItem = intent.getParcelableExtra<MasterResModelItem>(Constant.MASTER_DATA_ITEM)
        AppBizLogger.log(AppBizLogger.LoggingType.INFO,masterResModelItem.toString())

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.vehicle))

        val productCategoriesViewModel= ViewModelProviders.of(this).get(ProductCategoriesViewModel::class.java)

        productCategoriesViewModel.getProductCateg(masterResModelItem.name).observe(this,
            ApiObserver<ProductCategoriesResModel>(this,object :ChangedListener<ProductCategoriesResModel>{
                override fun onSuccess(productCategoriesResModel: ProductCategoriesResModel) {
                    // no found product
                    productCategoriesResModel.vehicles?.let {
                    rec_veichle.adapter= ProductCategoriesAdapter(it,this@ProductCategoryActivity)
                    arrayList= it as ArrayList<Vehicle>

                    // searching on products
                     search(arrayList)
                        return
                    }

                    Toast.makeText(this@ProductCategoryActivity, ValidationMessage.NO_DATA_FOUND, Toast.LENGTH_LONG).show()

                }
            }))

    }

   /* override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToViewDetailsActivity(this)
    }*/


    private fun search(arrayList: ArrayList<Vehicle>){

        ed_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(s.isEmpty()) Common.hideSoftKeyBoard(this@ProductCategoryActivity,ed_search)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
               val textlength = ed_search.text.length
                arrayListSort.clear()
                for (i in 0 until arrayList.size) {
                    if (textlength <= arrayList[i].category_name.length) {
                        if (arrayList[i].category_name.toLowerCase().trim().contains(
                                ed_search.text.toString().toLowerCase().trim()
                            )) {
                            arrayListSort.add(arrayList[i])
                        }
                    }
                }
                rec_veichle.adapter= ProductCategoriesAdapter(arrayListSort,this@ProductCategoryActivity
                   )
           if(arrayListSort.size==0){
               layout_vehicle_not_found.visibility=View.VISIBLE
               sv.scrollTo(5, resources.getDimension(R.dimen._300sdp).roundToInt())
               notify_admin.setOnClickListener {
                   MoveToAnotherComponent.moveToNotifyAdminActivity(this@ProductCategoryActivity)
               }
           }else
               layout_vehicle_not_found.visibility=View.GONE
            }
        })
    }


}


