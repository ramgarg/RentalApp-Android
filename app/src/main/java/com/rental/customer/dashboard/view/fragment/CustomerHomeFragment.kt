package com.rental.customer.dashboard.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.rental.R
import com.rental.common.model.modelclass.MasterResModel
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.view.ApiResult
import com.rental.common.view.LiveDataFragmentClass
import com.rental.common.viewmodel.MasterDataViewModel
import com.rental.customer.dashboard.view.adapter.CustomerHomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class CustomerHomeFragment : Fragment(),ApiResult{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        LiveDataFragmentClass(this).let {
            it.observeApiResult<MasterResModel, LifecycleOwner, Context>(
                it.callAPIFragment<MasterDataViewModel, Fragment>(this).getMasterData()
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


