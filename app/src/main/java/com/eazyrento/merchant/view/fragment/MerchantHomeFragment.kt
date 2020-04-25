package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.model.modelclass.ProductCateItem
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.MerchantProductItem
import com.eazyrento.merchant.view.activity.MerchantProductCategory
import com.eazyrento.merchant.view.adapter.MerchantHomeCateAdapter
import com.eazyrento.merchant.viewModel.MerchantProductCategoriesViewModel
import com.eazyrento.supporting.MyJsonParser
import com.google.gson.JsonElement
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_merchant_home.*

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
        if(data is JsonElement){

            val keys = data.asJsonObject.keySet()
            val merchantCatItemList = ArrayList<MerchantCatItem>()

            for (key in keys){
            val list:List<MerchantProductItem>? = MyJsonParser.convertJSONListIntoList(MyJsonParser.JsonArrayFromJsonObject
                (data.asJsonObject,key))

                list?.let {merchantCatItemList.add(MerchantCatItem(key,it))}

         }
            merchant__recycl_cate_view.adapter = MerchantHomeCateAdapter(requireContext(),merchantCatItemList,this)
        }

    }


    override fun <T, K> onViewClick(type: T, where: K) {
        MoveToAnotherComponent.openActivityWithParcelableParam<MerchantProductCategory,T>(requireContext(),Constant.INTENT_MERCHANT_PRODUCT_LIST,type)
    }

}
@Parcelize
data class MerchantCatItem(val key: String,val value:List<MerchantProductItem>): Parcelable