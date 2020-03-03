package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.view.adapter.VehicleDetailsAdapter
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.utils.MoveToActivity
import com.rental.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_view_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ViewDetailActivity :AppCompatActivity() {
    private lateinit var homeViewModel:HomeViewModel
    private var likeUnlike:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_details)

        ViewVisibility.isVisibleOrNot(
            this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.details))

        btn_next.setOnClickListener {
            MoveToActivity.moveToBookingDetailsActivity(this)
        }

        homeViewModel= ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.getHomeResponse().observe(this, Observer {
            rec_veichle_details.adapter= VehicleDetailsAdapter(it.data,this)
        })

        img_like_unlike.setOnClickListener {
            if(likeUnlike) {
                likeUnlike=false
                img_like_unlike.setImageResource(R.mipmap.like)
            }
            else {
                likeUnlike=true
                img_like_unlike.setImageResource(R.mipmap.unlike)
            }
        }
    }
}