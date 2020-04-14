package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.R
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.view.fragment.BaseFragment
import com.rental.common.viewmodel.MasterDataViewModel
import com.rental.customer.dashboard.view.adapter.CustomerHomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class CustomerHomeFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<MasterDataViewModel>(this).getMasterData()
                , viewLifecycleOwner, requireActivity()
            )
        }

    }


    override fun <T> onSuccessApiResult(data: T) {
        rec_veichle.adapter = CustomerHomeAdapter(
            data as ArrayList<MasterResModelItem>,
            requireActivity()
        )
    }
}


