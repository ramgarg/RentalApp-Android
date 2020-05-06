package com.eazyrento.merchant.view.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.MerchantProductItem
import com.eazyrento.merchant.view.activity.AddProductDailogActivity
import com.eazyrento.merchant.view.activity.MerchantAddVehicleActivity
import com.eazyrento.merchant.view.activity.MerchantMainActivity
import com.eazyrento.merchant.view.activity.MerchantProductCategory
import com.eazyrento.merchant.view.adapter.MerchantHomeCateAdapter
import com.eazyrento.merchant.viewModel.MerchantDeleteProductViewModel
import com.eazyrento.merchant.viewModel.MerchantProductCategoriesViewModel
import com.eazyrento.supporting.MyJsonParser
import com.google.gson.JsonElement
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_merchant_home.*

class MerchantHomeFragment : BaseFragment() {
    private var isDeleteProject:Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_merchant_home, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        merchant_add_vehicle_btn.setOnClickListener{ MoveToAnotherComponent.moveToActivityNormal<MerchantAddVehicleActivity>(requireContext()) }

        isDeleteProject = false

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
            if (isDeleteProject){

                isDeleteProject = false

                Common.showToast(requireContext(),ValidationMessage.PRODUCT_DELETE_SUCCESS)

                (requireActivity() as MerchantMainActivity).setHomeFragMent()

//                merchant__recycl_cate_view.adapter?.notifyDataSetChanged()
                return
            }
            val keys = data.asJsonObject.keySet()
            val merchantCatItemList = ArrayList<MerchantCatItem>()

            for (key in keys){
            val list:List<MerchantProductItem>? = MyJsonParser.convertJSONListIntoList(MyJsonParser.JsonArrayFromJsonObject
                (data.asJsonObject,key))

                list?.let {merchantCatItemList.add(MerchantCatItem(key,it))}

         }
            //home outer adapter
            merchant__recycl_cate_view.adapter = MerchantHomeCateAdapter(requireContext(),merchantCatItemList,this)
        }

    }


    override fun <T, K> onViewClick(type: T, where: K) {
        when(where){
            Constant.VIEW_ALL->MoveToAnotherComponent.startActivityResultWithParcelable<MerchantProductCategory,T>(requireActivity(),
                Constant.INTENT_MERCHANT_PRODUCT_LIST,type,Constant.MERCHANT_HOME_FRAGMENT_REQUEST_CODE)
            Constant.edit ->MoveToAnotherComponent.moveToActivityWithIntentValue<AddProductDailogActivity>(requireContext(),
                Constant.INTENT_MERCHANT_PRODUCT_EDIT,(type as MerchantProductItem).id)
            Constant.delete->deleteProduct(type)
        }

    }

    private fun <T> deleteProduct(type: T) {
        val obj = type as MerchantProductItem
        callAPI()?.let {
            isDeleteProject = true
            it.observeApiResult(
                it.callAPIFragment<MerchantDeleteProductViewModel>(this)
                    .deletePoductAPI(obj.id)
                , viewLifecycleOwner, requireContext()
            )


        }
    }

}
@Parcelize
data class MerchantCatItem(val key: String,val value:List<MerchantProductItem>): Parcelable