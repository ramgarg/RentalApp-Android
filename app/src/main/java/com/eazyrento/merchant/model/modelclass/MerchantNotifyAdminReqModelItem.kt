package com.eazyrento.merchant.model.modelclass

data class MerchantNotifyAdminReqModelItem(
        var name: String?,
        var capacity: String?,
        var power: String?,
        var price: String?,
        var fuel_type: String?,
        var desc: String?

    ){
        constructor():this("","","","","","")
    }