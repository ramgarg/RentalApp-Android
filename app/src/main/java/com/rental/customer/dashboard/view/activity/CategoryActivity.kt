package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.adapter.CategoryAdapter
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import com.rental.customer.utils.Common
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.category_activity.*
import kotlinx.android.synthetic.main.fragment_home.rec_veichle
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.math.roundToInt


class CategoryActivity :AppCompatActivity(),RecyclerViewItemClick {

    private lateinit var homeViewModel:HomeViewModel
    private lateinit var arrayList:ArrayList<Data>
     var arrayListSort:ArrayList<Data> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       setContentView(R.layout.category_activity)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.vehicle))

        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.getHomeResponse().observe(this, Observer {
            rec_veichle.adapter= CategoryAdapter(it.data,this,this)
            arrayList= it.data as ArrayList<Data>

            search(arrayList)
        })
    }

    override fun onItemClick(item: Data) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        MoveToAnotherComponent.moveToViewDetailsActivity(this)
    }


    private fun search(arrayList: ArrayList<Data>){

        ed_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(s.isEmpty()) Common.hideSoftKeyBoard(this@CategoryActivity,ed_search)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
               val textlength = ed_search.text.length
                arrayListSort.clear()
                for (i in 0 until arrayList.size) {
                    if (textlength <= arrayList[i].first_name.length) {
                        if (arrayList[i].first_name.toLowerCase().trim().contains(
                                ed_search.text.toString().toLowerCase().trim()
                            )) {
                            arrayListSort.add(arrayList[i])
                        }
                    }
                }
                rec_veichle.adapter= CategoryAdapter(arrayListSort,this@CategoryActivity,
                    this@CategoryActivity)
           if(arrayListSort.size==0){
               layout_vehicle_not_found.visibility=View.VISIBLE
               sv.scrollTo(5, resources.getDimension(R.dimen._300sdp).roundToInt())
               notify_admin.setOnClickListener {
                   MoveToAnotherComponent.moveToNotifyAdminActivity(this@CategoryActivity)
               }
           }else
               layout_vehicle_not_found.visibility=View.GONE
            }
        })
    }


}


