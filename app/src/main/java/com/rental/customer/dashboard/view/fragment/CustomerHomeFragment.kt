package com.rental.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rental.Constant
import com.rental.R
import com.rental.common.model.modelclass.MasterResModelItem
import com.rental.common.view.fragment.BaseFragment
import com.rental.common.viewmodel.MasterDataViewModel
import com.rental.customer.dashboard.view.activity.ProductCategoryActivity
import com.rental.customer.dashboard.view.adapter.CustomerHomeAdapter
import com.rental.customer.utils.Common
import com.rental.customer.utils.MoveToAnotherComponent
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
            requireActivity(),this
        )
    }

    override fun <T,K> onViewClick(type: T, where:K) {
        MoveToAnotherComponent.openActivityWithParcelableParam<ProductCategoryActivity,T>(requireContext(),
            Constant.MASTER_DATA_ITEM,type)
        /*val intent = Intent(context, ProductCategoryActivity::class.java).putExtra(Constant.MASTER_DATA_ITEM,listMasterData.get(position))
        context.startActivity(intent)*/
    }
}


