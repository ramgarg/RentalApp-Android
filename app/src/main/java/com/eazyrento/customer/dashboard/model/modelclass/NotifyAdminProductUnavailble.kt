package com.eazyrento.customer.dashboard.model.modelclass

data class NotifyAdminProductUnavailble(
    var name: String?,
    var capacity: String?,
    var description: String?
) {
    constructor():this("","","")
}

