package com.rental.customer.dashboard.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.rental.R
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import kotlinx.android.synthetic.main.row_spinner.view.*

class VehicleSpinnerAdapter(ctx: Context,
                            spinner: List<Data>) :
    ArrayAdapter<Data>(ctx, 0, spinner) {
    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val spinner = getItem(position)
        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.row_spinner,
            parent,
            false
        )
        view.vehicle_category.text = spinner?.first_name
        return view
    }
}