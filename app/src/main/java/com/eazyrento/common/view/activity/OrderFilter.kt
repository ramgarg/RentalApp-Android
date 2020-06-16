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

        setSppinerData( R.array.UpdateStatus,R.id.sp_status)
        setSppinerData( R.array.ServiceDate,R.id.sp_service_date)
        setSppinerData( R.array.CustomerName,R.id.sp_customer_name)
        setSppinerData( R.array.OrderId,R.id.sp_order_id)

        topBarWithBackIconAndTitle(resources.getString(R.string.filter))

    }
    
    fun getSpinnerDataByID(int: Int): Array<String> {
        return resources.getStringArray(int)
    }

    private fun setSppinerData(spinerDataReSource:Int,spinerName:Int) {
//        R.array.RegistrationDocument
        //R.id.sp_select_document
        val data = getSpinnerDataByID(spinerDataReSource)
        val spinner = findViewById<Spinner>(spinerName)

        spinner?.let {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
            it.adapter = adapter

            it.onItemSelectedListener = spinnerListnere
        }
    }

    val spinnerListnere = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if(position==0){

            }
            else{
                when(parent.id){
                    R.id.sp_customer_name ->{
                       // merchantAddProductReqModel.variant = getSpinnerDataByID(R.array.FuelType)[position]
                    }
                    R.id.sp_order_id ->{

                       }
                    R.id.sp_status ->{

                        }
                    R.id.sp_service_date ->{

                       }
                }

            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // write code to perform some action
        }
    }

}