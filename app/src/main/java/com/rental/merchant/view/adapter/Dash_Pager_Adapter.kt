package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rental.R
import kotlinx.android.synthetic.main.merchant_dash_row.view.*

class PostsAdapter(val posts: ArrayList<String>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dashCard: TextView = itemView.findViewById(R.id.merchant_dash_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.merchant_dash_row,
            parent,
            false
        ) //To change body of created functions use File | Settings | File Templates.
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size
    //To change body of created functions use File | Settings | File Templates.


    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        holder.dashCard.text = posts[position] //To change body of created functions use File | Settings | File Templates.
}
}