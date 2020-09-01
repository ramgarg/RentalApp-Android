package com.eazyrento.common.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.ORDER_ID
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.PRODUCT_NAME
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.START_DATE
import com.eazyrento.common.view.activity.FilterKeyValue.Companion.STATUS
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import kotlinx.android.synthetic.main.activity_filter.*
import java.lang.Exception

class OrderFilter : BaseActivity() {

    private var listOrderItems: List<OrderDetailsResModel>? = null
    private lateinit var arrayStatus: Array<String>
    private lateinit var arrayOrderID: Array<String>
    private lateinit var arrayProductName: Array<String>
    private lateinit var arrayDate: Array<String>

    private var hashMapFilter = HashMap<String, String>()


    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        topBarWithBackIconAndTitle(resources.getString(R.string.filter))

        // get list from intent  listOrderItems from OrderListFragment......

         listOrderItems = intent.getParcelableArrayListExtra(Constant.INTENT_FILTER_LIST)

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,listOrderItems.toString())


        if (setFilterArrays()) {

            setSppinerData(arrayStatus, R.id.sp_status)
            setSppinerData(arrayDate, R.id.sp_service_date)
            setSppinerData(arrayProductName, R.id.sp_product_name)
            setSppinerData(arrayOrderID, R.id.sp_order_id)
        }

    }

    private fun setFilterArrays(): Boolean {

        try {
            listOrderItems?.let {

                val size = it.size+1

                arrayStatus = Array(size) {resources.getString(R.string.SELECT_STATUS)}
                arrayOrderID = Array(size) {resources.getString(R.string.SELECT_ORDER_ID)}
                arrayProductName = Array(size) {resources.getString(R.string.SELECT_PRODUCT_NAME)}
                arrayDate = Array(size) {resources.getString(R.string.SELECT_DATE)}


                for (i in 0 until size-1) {

                    arrayStatus[i+1] = it[i].status
                    arrayOrderID[i+1] = it[i].order_id

                    it[i].product_detail?.let { inner ->
                        arrayProductName[i+1] = inner.product_name
                        arrayDate[i+1] = ""+inner.start_date
                    }

                }
                return true
            }
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    private fun setSppinerData(data: Array<String>, spinerName: Int) {

//        val data = getSpinnerDataByID(spinerDataReSource)
        val spinner = findViewById<Spinner>(spinerName)

        spinner?.let {
            val adapter = ArrayAdapter(this, R.layout.spinner_item_style, data)
            it.adapter = adapter

            it.onItemSelectedListener = spinnerListnere
        }
    }

    private fun getSpinnerDataByID(int: Int): Array<String> {
        return resources.getStringArray(int)
    }
    private fun setMapFilterValue(key:String,value:String){
        hashMapFilter[key] = value
    }

    private val spinnerListnere = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                when (parent.id) {
                    R.id.sp_order_id -> {
                        hashMapFilter[ORDER_ID] = arrayOrderID[position]
                    }
                    R.id.sp_product_name -> {
                        hashMapFilter[PRODUCT_NAME] = arrayProductName[position]
                    }
                    R.id.sp_status -> {
                        hashMapFilter[STATUS] = arrayStatus[position]
                    }
                    R.id.sp_service_date -> {
                        hashMapFilter[START_DATE] = arrayDate[position]
                    }
                }

        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // write code to perform some action
        }
    }

    fun applyClick(view: View){
            finishCurrentActivityWithResult(Activity.RESULT_OK, Intent().putExtra(Constant.INTENT_FILTER_APPLY,hashMapFilter))
    }
    fun resetClick(view: View){

        sp_order_id.setSelection(0)
        sp_product_name.setSelection(0)
        sp_service_date.setSelection(0)
        sp_status.setSelection(0)

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,"map:".plus(hashMapFilter.toString()))

    }

}

interface FilterKeyValue {

    companion object {

        const val ORDER_ID = "order_id"
        const val STATUS = "status"
        const val PRODUCT_NAME = "product_name"
        const val START_DATE = "start_date"

    }

}