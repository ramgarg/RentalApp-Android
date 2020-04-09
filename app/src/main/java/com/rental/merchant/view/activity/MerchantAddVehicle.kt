package com.rental.merchant.view.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.viewmodel.CustomerHomeViewModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.toolbar.*

class MerchantAddVehicle : AppCompatActivity(), RecyclerViewItemClick {

    private lateinit var merchantViewModelCustomer: CustomerHomeViewModel
    private lateinit var arrayList:ArrayList<Data>
    var arrayListSort:ArrayList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.merchant_add_vehicle)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.vehicle))

        typeSpinnerData()
        categorySpinnerData()
        subCategorySpinnerData()

       /* merchantViewModelCustomer= ViewModelProviders.of(this).get(CustomerHomeViewModel::class.java)
        merchantViewModelCustomer.getHomeResponse().observe(this, Observer {
            rec_add_veichle.adapter= CategoryAdapter(it.data,this,this)
            arrayList= it.data as ArrayList<Data>

            //search(arrayList)
        })*/

    }

    private fun subCategorySpinnerData() {
        val select_subcategory = resources.getStringArray(R.array.SelectSubCategory)

        val spinner = findViewById<Spinner>(R.id.sp_select_subcategory)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, select_subcategory)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MerchantAddVehicle, getString(R.string.selected_item) + " " + "" + select_subcategory[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun categorySpinnerData() {
        val select_category = resources.getStringArray(R.array.SelectCategory)

        val spinner = findViewById<Spinner>(R.id.sp_select_category)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, select_category)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MerchantAddVehicle, getString(R.string.selected_item) + " " + "" + select_category[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun typeSpinnerData() {
        val select_type = resources.getStringArray(R.array.SelectType)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.sp_select_type)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, select_type)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@MerchantAddVehicle, getString(R.string.selected_item) + " " + "" + select_type[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    override fun onItemClick(item: Data) {
        Common.showDialog("UserDay","",this,R.layout.add_vehicle_dialog)
    }
}