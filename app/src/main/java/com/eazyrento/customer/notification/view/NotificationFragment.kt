package com.eazyrento.customer.notification.view

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.eazyrento.R
import com.eazyrento.ValidationMessage
import com.eazyrento.agent.view.BaseNavigationActivity
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.common.view.fragment.BaseFragment
import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationModel
import com.eazyrento.customer.notification.viewmodel.NotificationViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.supporting.*
import com.eazyrento.supporting.DeeplinkEvents.Companion.KEY_DEEPLINK
import com.eazyrento.supporting.DeeplinkEvents.Companion.KEY_ORDER_ID
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.notification_row.view.*
import java.lang.Exception

//"Notifications"

class NotificationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_notification, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notificationIconVisility(View.INVISIBLE)

        callAPI()?.let {
            it.observeApiResult(
                it.callAPIFragment<NotificationViewModel>(this).getNotificationList()
                , viewLifecycleOwner, requireActivity()
            )
        }

    }

    private fun notificationIconVisility(int:Int){
        (requireActivity() as BaseNavigationActivity).setVisiblity(int)
    }

    override fun <T> onSuccessApiResult(data: T) {

        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())
    if (data is NotificationList) {
        if (data.isEmpty()) {
            Common.showToast(requireContext(), ValidationMessage.NO_DATA_FOUND)
            return
        }
        rec_notification.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)

        rec_notification.addItemDecoration(DividerItemDecoration(requireActivity(),VERTICAL))

        rec_notification.adapter = NotificationAdapter(requireActivity(),data)
    }
    }

    override fun onDestroyView() {
        notificationIconVisility(View.VISIBLE)
        super.onDestroyView()

    }
}

class NotificationAdapter(val activity: Activity,val listNotification:List<NotificationModel>):RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

    class NotificationViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tv_notification_msg = view.tv_noti_msg
        val tv_notification_date = view.tv_noti_date
        val img_notification_icon = view.img_notification_icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(LayoutInflater.from(activity).inflate(R.layout.notification_row,parent,false))

    }

    override fun getItemCount(): Int {
        return listNotification.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {

        try {
        holder.tv_notification_msg.text = listNotification[position].sent_message

        holder.itemView.setOnClickListener{

            listNotification[position].deep_link?.let { deeplink ->

                val mutableMap = mapOf(KEY_DEEPLINK to deeplink , KEY_ORDER_ID to listNotification[position].obj_id)
                DeeplinkEvents.mapPayLoadDataDeeplink = mutableMap
                (activity as BaseNavigationActivity).let {
                    it.pageNavigationAtDeeplink(DeeplinkEvents.mapPayLoadDataDeeplink)
                    // readed
                    holder.itemView.setBackgroundColor(activity.resources.getColor(R.color.notifiction_read))

                }
            }

        }
        setNotificationIcon(listNotification[position],holder.img_notification_icon)

        val listFormat = splitDateServerFormat(listNotification[position].added_on)
        holder.tv_notification_date.text = getDisplayDate(listFormat[0].toInt(),listFormat[1].toInt(),listFormat[2].toInt())

        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun setNotificationIcon(
        noti: NotificationModel,
        imgNotificationIcon: ImageView){
        when(noti.deep_link){
            DeeplinkEvents.BOOKINGS->{imgNotificationIcon.setImageResource(R.mipmap.booking_accepted)}
            DeeplinkEvents.ORDER_SUMMARY->{imgNotificationIcon.setImageResource(R.mipmap.order_updated)}
            DeeplinkEvents.ORDER_LISTING->{imgNotificationIcon.setImageResource(R.mipmap.order_updated)}
            DeeplinkEvents.PAYMENT ->{imgNotificationIcon.setImageResource(R.mipmap.payment_noti)}
            else->{imgNotificationIcon.setImageResource(R.mipmap.notification)}

        }
    }
}