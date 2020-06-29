package com.eazyrento.customer.notification.view

import android.app.Activity
import android.os.Bundle
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
import com.eazyrento.customer.notification.model.*
import com.eazyrento.customer.notification.view.OprationNotification.Companion.SINGLE_OPTION
import com.eazyrento.customer.notification.viewmodel.NotificationViewModel
import com.eazyrento.customer.utils.Common
import com.eazyrento.supporting.*
import com.eazyrento.supporting.DeeplinkEvents.Companion.KEY_DEEPLINK
import com.eazyrento.supporting.DeeplinkEvents.Companion.KEY_ORDER_ID
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.notification_row.view.*
import kotlinx.android.synthetic.main.thank_you_pop.*
import java.lang.Exception

//"Notifications"

class NotificationFragment : BaseFragment(),OprationNotification {
    private var mNotifcationID:Int?=null
    private var mNotificationList:NotificationList?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_notification, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notificationIconVisility(View.INVISIBLE)
        btn_clear_all_notification.setOnClickListener {
            deleteNotificationAPI(NotificationDeleteModel(null,"all"))
        }

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

    fun deleteNotificationAPI(notifcationDeleteModel: NotificationDeleteModel) {
        callAPI()?.let {inner->
            inner.observeApiResult(
                inner.callAPIFragment<NotificationViewModel>(this).deleteNotification(notifcationDeleteModel)
                , viewLifecycleOwner, requireActivity()
            )
        }
    }

     override fun onOperation(opr:Int,baseNotificationOpration: BaseNotificationOpration){
        when(opr){
            OprationNotification.DELETE ->{

                val dialog = showDialogCustomDialog(requireActivity())
                dialog.tv_msg.text = getString(R.string.delete_notification)

                dialog.btn_cancle.let {

                    it.visibility = View.VISIBLE
                    it.text = getString(R.string.no)
                    it.setOnClickListener {
                        dialog.cancel()
                    }
                }
                dialog.btn_ok.let {

                    it.text = getString(R.string.yes)

                   val notifcationDeleteModel = baseNotificationOpration as NotificationDeleteModel
                    mNotifcationID = notifcationDeleteModel.notification_id

                    it.setOnClickListener {
                        dialog.cancel()
                        deleteNotificationAPI(notifcationDeleteModel)
                    }
                }
                dialog.show()


            }
            OprationNotification.READ ->{
                callAPI()?.let {
                    it.observeApiResult(
                        it.callAPIFragment<NotificationViewModel>(this).readNotification(baseNotificationOpration as NotificationReadModel)
                        , viewLifecycleOwner, requireActivity()
                    )
                }
            }
        }

    }

    override fun <T> onSuccessApiResult(data: T) {

     AppBizLogger.log(AppBizLogger.LoggingType.DEBUG,data.toString())

    if (data is JsonElement){
        Common.showToast(requireContext(),ValidationMessage.REQUEST_SUCCESSED)

        // delete item from list
        if (mNotifcationID!=null && mNotificationList.isNullOrEmpty().not()){
            try {
                val notificationModel = mNotificationList?.single { notificationModel -> notificationModel.id==mNotifcationID }
                mNotificationList?.remove(notificationModel)

                rec_notification.adapter?.notifyDataSetChanged()

            }catch (e:Exception){
                e.printStackTrace()
            }

        }
        return
    }
    if (data is NotificationList) {
        if (data.isEmpty()) {
            Common.showToast(requireContext(), ValidationMessage.NO_DATA_FOUND)
            return
        }

        mNotificationList = data

        rec_notification.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)

        rec_notification.addItemDecoration(DividerItemDecoration(requireActivity(),VERTICAL))

        rec_notification.adapter = NotificationAdapter(requireActivity(),data,this)
    }
    }

    override fun onDestroyView() {
        notificationIconVisility(View.VISIBLE)
        super.onDestroyView()

    }

}

class NotificationAdapter(val activity: Activity,val listNotification:List<NotificationModel>,val oprationNotification:OprationNotification):RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

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

        // already readed
        if(listNotification[position].is_read)
            holder.itemView.setBackgroundColor(activity.resources.getColor(R.color.notifiction_read))

        holder.itemView.setOnClickListener{
            setClickListnerOnItemView(position,holder)
        }
        holder.itemView.setOnLongClickListener {
       
            oprationNotification.onOperation(
                OprationNotification.DELETE,
                NotificationDeleteModel(listNotification[position].id, SINGLE_OPTION)
            )
            return@setOnLongClickListener true
        }
        setNotificationIcon(listNotification[position],holder.img_notification_icon)

        val listFormat = splitDateServerFormat(listNotification[position].added_on)
        holder.tv_notification_date.text = convertToDisplayDate(listFormat)

        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    private fun setNotificationIcon(
        noti: NotificationModel,
        imgNotificationIcon: ImageView){
        when(noti.deep_link){
            DeeplinkEvents.BOOKINGS->{imgNotificationIcon.setImageResource(R.mipmap.booking_accepted)}
            DeeplinkEvents.ORDER_SUMMARY->{imgNotificationIcon.setImageResource(R.mipmap.order_updated)}
            DeeplinkEvents.ORDER_LISTING->{imgNotificationIcon.setImageResource(R.mipmap.order_updated)}
            DeeplinkEvents.PAYMENT ->{imgNotificationIcon.setImageResource(R.mipmap.payment_noti)}
            else->{imgNotificationIcon.setImageResource(R.drawable.alert)}

        }
    }
   private fun setClickListnerOnItemView(position: Int,holder: NotificationViewHolder){
       // first time reading
       if(listNotification[position].is_read.not()) {
           // readed
           listNotification[position].is_read = true
           holder.itemView.setBackgroundColor(activity.resources.getColor(R.color.notifiction_read))
           oprationNotification.onOperation(
               OprationNotification.READ,
               NotificationReadModel(listNotification[position].id, SINGLE_OPTION)
           )
       }

       //deeplinking
       listNotification[position].deep_link?.let { deeplink ->

           val mutableMap = mapOf(KEY_DEEPLINK to deeplink , KEY_ORDER_ID to listNotification[position].obj_id)
           DeeplinkEvents.mapPayLoadDataDeeplink = mutableMap

           (activity as BaseNavigationActivity).pageNavigationAtDeeplink(DeeplinkEvents.mapPayLoadDataDeeplink)
       }

   }
}

interface OprationNotification{
    companion object{
        const val DELETE = 0
        const val  READ = 1

        const val SINGLE_OPTION ="single"


    }
    fun onOperation(opr:Int,baseNotificationOpration: BaseNotificationOpration)
}