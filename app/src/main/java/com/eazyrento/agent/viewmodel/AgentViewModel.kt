package com.eazyrento.agent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.agent.model.modelclass.*
import com.eazyrento.agent.model.repositry.*
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderReqResModel
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderUpdateReqModel

import com.google.gson.JsonElement


class AgentBookingDetailsViewModel : ViewModel() {

    fun agentBookingDetails(id: Int): LiveData<DataWrapper<OrderDetailsResModel>> {
        return AgentBookingDetailsRepo().agentBookingDetails(id)

    }
}

class AgentAssignMerchantViewModel : ViewModel() {

    fun assignMerchants(assignMerchantsReqModel: AssignMerchantsReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentMerchantsAssignRepo().assignMerchants(assignMerchantsReqModel)

    }
}

class AgentUpdateOrderViewModel : ViewModel() {

    fun updateOrder(id:Int,updateOrderModel: AgentUpdateOrderReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentUpdateOrderRepo().updateOrder(id,updateOrderModel)

    }
}

class AgentMerchantNeearByViewModel : ViewModel() {

    fun nearMerchantBy(id: Int): LiveData<DataWrapper<AgentMerchantFindNearByResModel>> {
        return AgentMerchantNearByRepo().getAgentMerchantNearBy(id)

    }
}

class AgentCreateNotesViewModel : ViewModel() {

    fun createNote(agentAddNoteReqModelItem: AgentAddNoteReqModelItem): LiveData<DataWrapper<AgentNotesListResModelItem>> {
        return AgentAddNotesRepo()
            .agentNotesAdd(agentAddNoteReqModelItem)
    }
}

class AgentNotesListViewModel : ViewModel() {

    fun getNotesList(): LiveData<DataWrapper<AgentNotesListResModel>> {
        return AgentNotesListRepo()
            .getAgentNotesList()
    }
}

class AgentDeleteNoteViewModel:ViewModel(){
    fun deleteNoteAPI(id:Int): LiveData<DataWrapper<JsonElement>> {
        return AgentNoteDeleteRepo().deleteNote(id)
    }

}

class AgentUpdateNoteViewModel:ViewModel(){
    fun updateNoteAPI(id:Int,agentAddNoteReqModelItem: AgentAddNoteReqModelItem): LiveData<DataWrapper<AgentNotesListResModelItem>> {
        return AgentUpdateNoteRepo().updateNote(id,agentAddNoteReqModelItem)
    }

}

class AgentFeedbackViewModel:ViewModel(){
    fun agentFeedback(agentFeedbackReqModel: AgentFeedbackReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentFeedbackRepo()
            .agentFeedback(agentFeedbackReqModel)
    }

}

class AgentSubOrderViewModel:ViewModel(){
    fun agentSubOrder(sub_order_id:Int): LiveData<DataWrapper<SubOrderReqResModel>> {
        return AgentSubOrderDetailRepo()
            .agentSubOrderBySubOrderID(sub_order_id)
    }

    fun agentSubOrderUpdate(sub_order_id:Int,resModel: SubOrderUpdateReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentSubOrderUpdateRepo()
            .agentSubOrderUpdate(sub_order_id,resModel)
    }
}

class AgentPaymentRecivedOrDeclineViewModel:ViewModel(){

    fun paymentAcceptOrDecline(paymentRecivedOrNotReqModel: PaymentRecivedOrNotReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentPaymentRecivedOrDeclineRepo()
            .agentPaymentRecivedOrDecline(paymentRecivedOrNotReqModel)
    }
}


