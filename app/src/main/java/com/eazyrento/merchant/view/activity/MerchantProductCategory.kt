package com.eazyrento.merchant.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.view.adapter.InfalterViewAdapter
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.merchant.view.fragment.MerchantCatItem
import kotlinx.android.synthetic.main.activity_merchant_all_product_home.*

class MerchantProductCategory :BaseActivity(), InfalterViewAdapter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_merchant_all_product_home)

        val merchantCatItem = intent.getParcelableExtra<MerchantCatItem>(
            Constant.INTENT_MERCHANT_PRODUCT_LIST
        )


        merchantCatItem.value?.let {
            rec_all_pro_home.adapter =
                ProductVehiclesAdapter(
                    it,
                    this,
                    this
                )

        }

    }
    override fun <T> moveOnSelecetedItem(type: T) {
    }
    override fun getInflaterViewIDAdapter(): Int {
        return R.layout.row_merchant_home_vehicle
    }

    override fun <T> setListnerOnView(view: View?, type: T, where: Int) {
    }
}