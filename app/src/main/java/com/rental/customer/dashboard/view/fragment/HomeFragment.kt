package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rental.R

class HomeFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    // Creates a vertical Layout Manager
//        rec_veichle.layoutManager = LinearLayoutManager(this)
//        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rec_veichle.layoutManager = GridLayoutManager(this, 3)
//        // Access the RecyclerView Adapter and load the data into it
//        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        homeViewModel.getArticleResponseLiveData().observe(this, Observer {
//
//            rec_veichle.adapter = DashBoardAdapter(veichleList, this)
//        })
//

}
