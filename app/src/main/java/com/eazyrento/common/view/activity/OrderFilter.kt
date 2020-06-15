package com.eazyrento.common.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel

class OrderFilter : BaseActivity() {

    lateinit var listOrderItems: CustomerOrderListResModel

    private var selectName:String?=null
    private var selectOrder:String?=null
    private var selectStatus:String?=null
    private var selectService:String?=null


    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        customerSpinnerData()
        orderSpinnerData()
        statusSpinnerData()
        serviceDateSpinnerData()

        topBarWithBackIconAndTitle(resources.getString(R.string.filter))

    }

    private fun customerSpinnerData() {
        val sp_name = resources.getStringArray(R.array.CustomerName)

        val spinner = findViewById<Spinner>(R.id.sp_customer_name)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sp_name)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        selectName=null
                    }
                    else{
                        selectName = this@OrderFilter.resources.getStringArray(R.array.CustomerName)[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun orderSpinnerData() {
        val sp_order = resources.getStringArray(R.array.OrderId)

        val spinner = findViewById<Spinner>(R.id.sp_order_id)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sp_order)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        selectOrder=null
                    }
                    else{
                        selectOrder = this@OrderFilter.resources.getStringArray(R.array.OrderId)[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun serviceDateSpinnerData() {
        val sp_service_date = resources.getStringArray(R.array.ServiceDate)

        val spinner = findViewById<Spinner>(R.id.sp_service_date)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sp_service_date)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        selectService=null
                    }
                    else{
                        selectService = this@OrderFilter.resources.getStringArray(R.array.ServiceDate)[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun statusSpinnerData() {
        val sp_status = resources.getStringArray(R.array.UpdateStatus)

        val spinner = findViewById<Spinner>(R.id.sp_status)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sp_status)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){
                        selectStatus=null
                    }
                    else{
                        selectStatus = this@OrderFilter.resources.getStringArray(R.array.UpdateStatus)[position]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

}