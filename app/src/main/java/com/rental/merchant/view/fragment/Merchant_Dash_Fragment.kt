package com.rental.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PostsAdapter
import com.rental.R
import kotlinx.android.synthetic.main.merchant__dashboard__fragment.*

class Merchant_Dash_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.merchant_fragment_dash, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val posts: ArrayList<String> = ArrayList()

        for(i in 1..100){
            posts.add("Post # $i")
        }
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerview.adapter = PostsAdapter(posts)
    }
}