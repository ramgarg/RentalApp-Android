package com.eazyrento.agent.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.OrderBaseSummaryActivity

class AgentUpdateOrderActivity : OrderBaseSummaryActivity() {

    override fun <T> moveOnSelecetedItem(type: T) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_update_order_summary)
        updateStatusSpinnerData()


        /* ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
             toolbar_title, getString(R.string.order_summary))
 */
        //clickListenerOnViews()
    }

    private fun updateStatusSpinnerData() {
        val update_Status = resources.getStringArray(R.array.UpdateStatus)

        val spinner = findViewById<Spinner>(R.id.status_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, update_Status)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position==0){

                    }
                    else{
                        Toast.makeText(this@AgentUpdateOrderActivity, getString(R.string.selected_item) + " " + "" + update_Status[position], Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
    }
}