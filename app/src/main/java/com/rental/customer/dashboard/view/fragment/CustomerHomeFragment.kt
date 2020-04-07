package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rental.R
import com.rental.appbiz.retrofitapi.ApiObserver
import com.rental.appbiz.retrofitapi.ChangedListener
import com.rental.common.model.modelclass.MasterResModel
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.viewmodel.MasterDataViewModel
import com.rental.customer.dashboard.model.modelclass.Data
import com.rental.customer.dashboard.view.activity.CustomerMainActivity
import com.rental.customer.dashboard.view.adapter.CustomerHomeAdapter
import com.rental.customer.dashboard.viewmodel.CustomerHomeViewModel
import com.rental.customer.utils.MoveToAnotherComponent
import com.rental.customer.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class CustomerHomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

       val customerHomeViewModel = ViewModelProviders.of(this).get(MasterDataViewModel::class.java)

        customerHomeViewModel.getMasterData().observe(viewLifecycleOwner,
            ApiObserver<MasterResModel>(requireActivity(),object :ChangedListener<MasterResModel>{
                override fun onSuccess(dataWrapper: MasterResModel) {

                    rec_veichle.adapter = CustomerHomeAdapter(
                        dataWrapper as ArrayList<MasterResModelItem>,
                        requireActivity()
                    )
                }
            }))

        }

}


