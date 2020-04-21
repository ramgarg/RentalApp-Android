package com.rental.merchant.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.common.view.BaseActivity
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.myaddress.view.ListAddressActivity
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.ViewVisibility
import com.rental.merchant.view.adapter.MerchantAddressAdapter
import com.rental.merchant.viewModel.MerchantAddressViewModel
import kotlinx.android.synthetic.main.activity_merchant_address.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class MerchantAddressActivity : ListAddressActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}