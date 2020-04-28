package com.eazyrento.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.dashboard.model.modelclass.WishListItem
import com.eazyrento.customer.dashboard.view.fragment.WishListFragment
import kotlinx.android.synthetic.main.row_wishlist.view.*

class WishListAdapter (val orderListing: MutableList<WishListItem>, val context: Context,val baseFragment: WishListFragment) : RecyclerView.Adapter<WishListAdapter.CardViewHolder>()  {


    class CardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tv_wish_name=view.tv_wish_name
        val tv_wish_price=view.tv_wish_price
        val tv_view_detail = view.tv_view_detail
        val tv_remove =view.tv_remove

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        holder.tv_wish_name?.text=orderListing.get(position).product_name
        holder.tv_wish_price?.text= Constant.DOLLAR+orderListing.get(position).price

       holder.tv_remove.setOnClickListener{
           baseFragment.delete(orderListing.get(position),position)
       }

        holder.tv_view_detail.setOnClickListener{
            baseFragment.viewDetails(orderListing.get(position),position)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_wishlist,
                    parent,
                    false
                )
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return orderListing.size
    }
}

/*
interface DeleteAndViewDetails{

    fun delete(wishListItem:WishListItem)
    fun viewDetails(wishListItem: WishListItem)
}*/
