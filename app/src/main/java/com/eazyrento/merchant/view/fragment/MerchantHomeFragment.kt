package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.viewModel.MerchantProductCategoriesViewModel

class MerchantHomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_merchant_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<MerchantProductCategoriesViewModel>(this)
                    .getProductCateg()
                , viewLifecycleOwner, requireContext()
            )

        }
    }

    override fun <T> onSuccessApiResult(data: T) {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
        //adapter

    }

    override fun <T, K> onViewClick(type: T, where: K) {
//        super.onViewClick(type, where)
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }

   /* override fun onItemClick(item: Data) {
        MoveToAnotherComponent.moveToCategoryActivity(requireContext())
    }*/
}