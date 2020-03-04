package com.rental.customer.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.WishListModel
import com.rental.customer.dashboard.view.adapter.OrderReviewAdapter
import com.rental.customer.dashboard.view.adapter.VehicleDetailsAdapter
import com.rental.customer.dashboard.viewmodel.HomeViewModel
import com.rental.customer.utils.Common
import com.rental.customer.utils.Common.Companion.wishListModel
import com.rental.customer.utils.MoveToAnotherComponent
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
            MoveToAnotherComponent.moveToBookingDetailsActivity(this)
        }

        homeViewModel= ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.getHomeResponse().observe(this, Observer {
            rec_veichle_details.adapter= VehicleDetailsAdapter(it.data,this)
        })

        img_like_unlike.setOnClickListener {
            if(likeUnlike) {
                likeUnlike=false
                //Here wishlist add data api will be called
                wishListModel.add(WishListModel("","Volvo VH Truck","15",1,5))
                img_like_unlike.setImageResource(R.mipmap.like)
            }
            else {
                likeUnlike=true
                img_like_unlike.setImageResource(R.mipmap.unlike)
            }
        }
    }



}