package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.BookingDetailsModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.dashboard.view.adapter.DeleteAndViewDetails
import com.eazyrento.customer.dashboard.view.adapter.OrderReviewAdapter
import com.eazyrento.customer.dashboard.view.adapter.WishListAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerCreateBookingViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.activity_order_review.*
import kotlinx.android.synthetic.main.toolbar.*


class CustomerBookingSubmitReviewActivity : BaseActivity(),DeleteAndViewDetails {
    companion object BookingList {
        val objListBookingItem = CustomerCreateBookingReqModel()

        fun setBookingItem( obj:CustomerCreateBookingReqModelItem){
            objListBookingItem.add(obj)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_review)

        ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_review))

        //creating our adapter
        val adapter =
            WishListAdapter(
                objListBookingItem,
                this,this
            )

        //now adding the adapter to recyclerview
        rec_order_review.adapter = adapter
    }

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun <T> onSuccessApiResult(data: T) {
        data?.let {
            showToast(ValidationMessage.BOOKING_SUBMITTED)

            MoveToAnotherComponent.moveToActivity<CustomerMainActivity>(this,
                Constant.INTENT_SUCCESS_ORDER_BOOKING,1)
        }
    }

    override fun onClickDailog(int: Int) {

        if(int==Constant.OK) {
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<CustomerCreateBookingViewModel>(this)
                        .createBooking(objListBookingItem)
                    , this, this
                )

            }
        }
    }

    fun onSubmitButtonClick(view: View){
        showDialog(getString(R.string.payment),ValidationMessage.BOOKING_SUBMITTED,this,R.layout.thank_you_pop)
    }

    override fun setHolderOnView(holder: WishListAdapter.CardViewHolder, position: Int) {

        holder.tv_pro_name?.text=objListBookingItem.get(position).projectDetails?.name
        holder.tv_booking_price?.text= Constant.DOLLAR +objListBookingItem.get(position).projectDetails?.base_price
        holder.tv_quantity.text = ""+objListBookingItem.get(position).quantity
        holder.pro_booking_days.text = ""+objListBookingItem.get(position).booking_days +" days"

        holder.lyt_booking_details.visibility = View.VISIBLE

        holder.tv_remove.setOnClickListener{
            objListBookingItem.removeAt(position)
            rec_order_review.adapter?.notifyDataSetChanged()
        }

        holder.tv_view_detail.setOnClickListener{
            MoveToAnotherComponent.moveToActivity<ProductDetailsActivity>(
                this,
                Constant.VEHICLES_SUB_CATE,objListBookingItem.get(position).projectDetails!!.id)

        }
     }


    }
