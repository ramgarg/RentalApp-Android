package com.eazyrento

interface Constant{
    companion object
    {
        const val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234
        //SECOND
        const val API_CONNECTION_TIMEOUT = 6*1000L
        const val API_READ_TIMEOUT = 6*1000L

        // key name
        const val MASTER_DATA_ITEM ="master_data_item"
        const val VEHICLES_DATA_ITEM ="vehicles"
        const val VEHICLES_SUB_CATE = "vehicle_sub"

        //intent key
         const val INTENT_SUCCESS_ORDER_BOOKING = "success"
        const val INTENT_MERCHANT_PRODUCT_LIST = "list"

        // Order status
        const val OPEN_ORDER = 1
        const val CLOSE_ORDER =3
        const val ORDER_SUMMERY_KEY ="orders_id"

        //Booking Dashboard
        const val BOOKING_DASHBOARD_AGENT =4
        const val BOOKING_DASHBOARD_MERCHANT =5

        //Booking List
        const val   BOOKING_LIST_CUSTOMER = 6
        const val BOOKING_LIST_AGENT = 7

        //Payment History
        const val CASH ="cash"
        const val PENDING="pending"
        const val RECEIVED="received"
        const val FAILED="failed"
        const val DOLLAR="$"
        const val QUANTITY="Quantity- "
        const val ORDER_ID="Order ID- "
        const val COMPLETED="completed"
        const val MERCHANT="Merchant"
        const val AGENT="Agent"
        const val CUSTOMER="Customer"

    }
}