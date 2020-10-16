package com.eazyrento.merchant.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.merchant.view.adapter.DriverAvailabilityAdapter
import kotlinx.android.synthetic.main.activity_driver_availability.*

class DriverAvailabilityActivity : BaseActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_driver_availability)

        topBarWithBackIconAndTitle(getString(R.string.driver))

        setDriverAvailabilityAdapter()

    }

    private fun setDriverAvailabilityAdapter() {

        rec_driver_list.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        val recycleAdapter = DriverAvailabilityAdapter(5, this)
        rec_driver_list.adapter = recycleAdapter
    }
}