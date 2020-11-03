package com.eazyrento.common.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazyrento.Constant
import com.eazyrento.R
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.customer.dashboard.model.modelclass.*
import com.eazyrento.customer.dashboard.view.activity.CustomerOrderSummaryActivity
import com.eazyrento.customer.dashboard.viewmodel.CustomerOrderDetailsViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.merchant.model.modelclass.FeedbackReqModel
import com.eazyrento.merchant.view.activity.MerchantOrderSummaryActivity
import com.eazyrento.merchant.view.activity.RateAndReviewActivity
import com.eazyrento.supporting.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_base_order_summary.*
import kotlinx.android.synthetic.main.adapter_users_order_summary.view.*
import kotlinx.android.synthetic.main.maintance_layout.*
import kotlinx.android.synthetic.main.order_summary_template.tv_end_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_end_time_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_date_sel
import kotlinx.android.synthetic.main.order_summary_template.tv_st_time_sel
import kotlinx.android.synthetic.main.phone_view.view.*
import kotlinx.android.synthetic.main.template_order_summery_top_view.*
import kotlinx.android.synthetic.main.template_work_info.*


 abstract class OrderBaseSummaryActivity : BaseActivity() {

    lateinit var orderRes:OrderDetailsResModel
    var listBaseUserRoleDetail = ArrayList<BaseUserRoleDetail>()

    override fun <T> moveOnSelecetedItem(type: T) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_order_summary)
    }

    protected fun setDataAndCallOrderDetailsAPI(int: Int){

        callAPIOrderList(int)

        topBarWithBackIconAndTitle(getString(R.string.order_summary))

    }

// order details
    private fun callAPIOrderList(id: Int){

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIActivity<CustomerOrderDetailsViewModel>(this)
                    .getOrderDetails(id)
                , this, this
            )
        }

    }


    override fun <T> onSuccessApiResult(data: T) {

         orderRes = data as OrderDetailsResModel

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

        tv_order_id.text= resources.getString(R.string.orderid).plus(orderRes.order_id)
        tv_booking_price.text= Constant.DOLLAR.plus(orderRes.order_amount_with_commission)

        orderRes.product_detail?.let {
            tv_order_product_name.text=it.product_name?.capitalize()
            order_product_quantity.text=resources.getString(R.string.quantity).plus(it.quantity)
            checkbox_with_driver.isChecked=it.with_driver

            setDateTime(it)
        }

        tv_work_location.text=orderRes.address_detail?.address_line

        setOrderStatus()

    }


    private fun setDateTime(prodet: ProductDetailX) {
        try {

            tv_st_date_sel.text= convertToDisplayDate(splitDateServerFormat(prodet.start_date))
            tv_end_date_sel.text= convertToDisplayDate(splitDateServerFormat(prodet.end_date))

            tv_st_time_sel.text= convertToDisplayTime(splitTimeServerFormat(prodet.start_time))

            tv_end_time_sel.text= convertToDisplayTime(splitTimeServerFormat(prodet.end_time))

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

     fun setUserRoleDetailsForMaintance(maintanceUserRoleView: MaintanceUserRoleView,base: BaseUserRoleDetail){

            maintanceUserRoleView.tv_user_name.text = base.full_name.capitalize()

            maintanceUserRoleView.tv_users_role.text = base.userRole

           phoneViewClickListener(maintanceUserRoleView,base)
           rattingAndReviewClickListener(maintanceUserRoleView,base)

            maintanceUserOrderStatus(maintanceUserRoleView)

            Picasso.with(this).load(base.profile_image).into(maintanceUserRoleView.img_user_pic)
    }

    private fun phoneViewClickListener(
        maintanceUserRoleView: MaintanceUserRoleView,
        base: BaseUserRoleDetail
    ) {
        when{

            this is CustomerOrderSummaryActivity && base is MerchantDetail -> maintanceUserRoleView.phone_view.visibility =View.GONE
            this is MerchantOrderSummaryActivity && base is CustomerDetailX -> maintanceUserRoleView.phone_view.visibility =View.GONE

            else -> maintanceUserRoleView.phone_view.setOnClickListener {
                Common.phoneCallWithNumber(base.mobile_number, this)
            }
        }

    }
    private fun rattingAndReviewClickListener(
        maintanceUserRoleView: MaintanceUserRoleView,
        base: BaseUserRoleDetail
    ) {
        maintanceUserRoleView.user_rating.setOnClickListener {

            val  feedbackReqModel = FeedbackReqModel(orderRes.order_id,null,null,null,"",0.0f)

            when (base) {
                is MerchantDetail -> feedbackReqModel.merchant_id =base.user_id
                is CustomerDetailX -> feedbackReqModel.customer_id = base.user_id
                is AgentDetail -> feedbackReqModel.agent_id = base.user_id
            }
            rateAndReview(feedbackReqModel)
        }
    }

    private fun setBaseUserRoleDetails(baseUserRoleDetail:BaseUserRoleDetail,userRole:String,id: Int){

        baseUserRoleDetail.userRole =userRole
        baseUserRoleDetail.user_id = id
        listBaseUserRoleDetail.add(baseUserRoleDetail)

    }
    //merchannt adapter
    protected fun setMaintanceUserRoleAdapter(cus: CustomerDetailX?,agent: AgentDetail?,merchantDetail: List<MerchantDetail>?) {

        listBaseUserRoleDetail.clear()

        if (cus!=null) {
            setBaseUserRoleDetails(cus,getString(R.string.customer),cus.id)

        }
        if (agent!=null){
            setBaseUserRoleDetails(agent,getString(R.string.agent),agent.id)
        }
        if (merchantDetail!=null && merchantDetail.isNotEmpty()){
            for (obj in merchantDetail){
                setBaseUserRoleDetails(obj,getString(R.string.merchant),obj.merchant_id)
            }
        }

        setInitialPramsForRecycle()

        maintance_reclye_view_user_role.adapter =
            MerchantMaintanceAdapter(listBaseUserRoleDetail,this)

    }
    private fun setInitialPramsForRecycle(){
        maintance_reclye_view_user_role.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false
        )
    }

    private fun maintanceUserOrderStatus(maintanceUserRoleView: MaintanceUserRoleView) {
        when(orderRes.order_status){
            Constant.COMPLETED ->{ maintanceUserRoleView.phone_view.visibility = View.GONE
                maintanceUserRoleView.user_rating.visibility = View.VISIBLE
            }
            else->maintanceUserRoleView.user_rating.visibility = View.GONE
        }
    }
    private fun setOrderStatus() {

        when(orderRes.order_status){
            Constant.COMPLETED ->{

                customer_payment_button.visibility=View.GONE

                // agent
                payment_view_history.visibility=View.VISIBLE
                agent_asign_merchant_and_request_payment.visibility = View.GONE
                agent_update_order_btn.visibility=View.GONE

            }
            Constant.PENDING ->{
                pending_amount.visibility=View.VISIBLE
                pending_amount.text= resources.getString(R.string.pending_).plus(orderRes.amount_to_pay)
            }
        }

    }

    private fun rateAndReview(feedbackReqModel: FeedbackReqModel) {

        MoveToAnotherComponent.openActivityWithParcelableParam<RateAndReviewActivity,FeedbackReqModel>(this,Constant.INTENT_RATE_REVIEWS,
            feedbackReqModel
        )
    }

}

data class MaintanceUserRoleView(
    val img_user_pic:ImageView,
    val tv_user_name :TextView,
    val tv_users_role:TextView,
    val phone_view:ImageView,
    val user_rating:TextView
)

// merchant maintance adapter
class MerchantMaintanceAdapter(val orderListing:List<BaseUserRoleDetail>, val context: Context) : RecyclerView.Adapter<MerchantMaintanceAdapter.CardViewHolder>() {

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val maintanceUserRoleView = MaintanceUserRoleView(view.img_user_pic,view.tv_user_name,view.tv_users_role,view.phone_view,view.user_rating)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val card_view =
            CardViewHolder(
                LayoutInflater.from(context).inflate(R.layout.adapter_users_order_summary, parent, false)
            )
        return card_view
    }

    override fun getItemCount(): Int {
        return orderListing.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val order_listing_obj =  orderListing.get(position)

        (context as OrderBaseSummaryActivity).setUserRoleDetailsForMaintance(holder.maintanceUserRoleView,order_listing_obj)

    }

}
