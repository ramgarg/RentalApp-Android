package com.eazyrento.customer.dashboard.view.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.amountWithCurrencyName
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModelItem
import com.eazyrento.customer.dashboard.view.adapter.DeleteAndViewDetails
import com.eazyrento.customer.dashboard.view.adapter.WishListAdapter
import com.eazyrento.customer.dashboard.viewmodel.CustomerCreateBookingViewModel
import com.eazyrento.customer.utils.MoveToAnotherComponent
import kotlinx.android.synthetic.main.activity_booking_details.*
import kotlinx.android.synthetic.main.activity_order_review.*


class CustomerBookingSubmitReviewActivity : BaseActivity(),DeleteAndViewDetails {
    private var removePosition:Int=0

    companion object BookingList {
        val objListBookingItem = CustomerCreateBookingReqModel()

        fun setBookingItem( obj:CustomerCreateBookingReqModelItem){
            objListBookingItem.add(obj)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_review)

        /*ViewVisibility.isVisibleOrNot(this, img_back, img_menu, img_notification,
            toolbar_title, getString(R.string.order_review))*/
        topBarWithBackIconAndTitle(getString(R.string.order_review))

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

            showToast(R.string.BOOKING_SUBMITTED)

            if (objListBookingItem.size>0){
                objListBookingItem.clear()
            }
            MoveToAnotherComponent.moveToActivityWithIntentValue<CustomerMainActivity>(this,
                Constant.KEY_INTENT_SUCCESS_ORDER_BOOKING,Constant.VALUE_INTENT_SUCCESS_ORDER_BOOKING)
        }
    }

    override fun onClickDailog(ok: Int) {

        if(ok==Constant.OK) {
            callAPI()?.let {
                it.observeApiResult(
                    it.callAPIActivity<CustomerCreateBookingViewModel>(this)
                        .createBooking(objListBookingItem)
                    , this, this
                )

            }
        }
        else if(ok==Constant.OK_REMOVE_PRODUCT)
        {
            objListBookingItem.removeAt(removePosition)
            rec_order_review.adapter?.notifyDataSetChanged()
        }
    }

    fun onSubmitButtonClick(view: View){

        if(objListBookingItem.size<=0){
            showToast(R.string.ADD_PRODUCT_AT_LEAST_ONE)
            return
        }
        showDialog(R.string.payment,resources.getString(R.string.BOOKING_SUBMITTED),this,R.layout.thank_you_pop,Constant.OK)
    }

    override fun setHolderOnView(holder: WishListAdapter.CardViewHolder, position: Int) {
        val item = objListBookingItem[position]
        holder.tv_pro_name?.text=item.projectDetails?.name?.capitalize()
        holder.tv_booking_price?.text= amountWithCurrencyName(item.projectDetails?.base_price)

        holder.tv_quantity.text = item.quantity.toString()

        holder.pro_booking_days.text = convertInDaysAndHour(item.bookingTimeInMin)

//        holder.pro_booking_days.text = item.booking_days.toString() +" day"


        objListBookingItem.get(position).address?.let {
            holder.tv_work_location.text = it.address_line.plus(",").plus(it.address_type)
        }

        holder.lyt_booking_details.visibility = View.VISIBLE

        holder.layout_remove.setOnClickListener{

            removePosition = position

            showDialog(R.string.payment,resources.getString(R.string.delete_product),this,R.layout.thank_you_pop,Constant.OK_REMOVE_PRODUCT)

            /*objListBookingItem.removeAt(position)
            rec_order_review.adapter?.notifyDataSetChanged()*/
        }

        holder.tv_view_detail.setOnClickListener{
            /*MoveToAnotherComponent.moveToActivityWithIntentValue<ProductDetailsActivity>(
                this,
                Constant.VEHICLES_SUB_CATE,objListBookingItem.get(position).projectDetails!!.id)*/
            finishCurrentActivity(Activity.RESULT_OK)

        }

        holder.add_quantity.setOnClickListener {

            item_quantity.text= (++item.quantity).toString()
        }

        holder.minus_quantity.setOnClickListener {
           if (objListBookingItem.get(position).quantity-1 <=0){
               showToast(R.string.FILL_QUANTITY)
           }
            else item_quantity.text= (--item.quantity).toString()
        }

     }

    private fun convertInDaysAndHour(inMin:Long?):String{
        var stringTimes=""
        inMin?.let {

            var hour = it/60
            val min = it%60

            if(hour>=24) {
                val days = hour / 24
                hour %= 24

                stringTimes=  stringTimes.plus(days).plus(getString(R.string.days)).plus(hour).plus(getString(
                                    R.string.hour)).plus(min).plus(getString(R.string.minute))
                return stringTimes
            }

            stringTimes=  stringTimes.plus(hour).plus(R.string.hour).plus(min).plus(R.string.minute)

            return stringTimes


        }
        return stringTimes
    }


    }
