package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.common.model.modelclass.MasterResModelItem
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.common.viewmodel.MasterDataViewModel
import com.eazyrento.customer.dashboard.view.activity.ProductCategoryActivity
import com.eazyrento.customer.dashboard.view.adapter.CustomerHomeAdapter
import com.eazyrento.customer.utils.MoveToAnotherComponent
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
        rec_veichle.adapter =
            CustomerHomeAdapter(
                data as ArrayList<MasterResModelItem>,
                requireActivity(), this
            )
    }

    override fun <T,K> onViewClick(type: T, where:K) {
        MoveToAnotherComponent.openActivityWithParcelableParam<ProductCategoryActivity,T>(requireContext(),
            Constant.MASTER_DATA_ITEM,type)
        /*val intent = Intent(context, ProductCategoryActivity::class.java).putExtra(Constant.MASTER_DATA_ITEM,listMasterData.get(position))
        context.startActivity(intent)*/
    }
}


