package com.eazyrento

interface Constant{
    companion object
    {
        const val PICK_PHOTO_FOR_AVATAR = 100
        const val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234
        //SECOND
        const val API_CONNECTION_TIMEOUT = 6*1000L
        const val API_READ_TIMEOUT = 6*1000L

        // key name
        const val MASTER_DATA_ITEM ="master_data_item"
        const val VEHICLES_DATA_ITEM ="vehicles"
        const val VEHICLES_SUB_CATE = "vehicle_sub"
        const val KEY_ADDRESS ="addr_id"

        const val BOOKING_PRODECT_DETAILS = "ID"

        //intent key
         const val INTENT_SUCCESS_ORDER_BOOKING = "success"
        const val INTENT_MERCHANT_PRODUCT_LIST = "list"
        const val INTENT_MERCHANT_PRODUCT_ADD = "add"
        const val INTENT_MERCHANT_PRODUCT_EDIT = "edit"
        const val INTENT_ADDRESS_EDIT = "edit"
        const val INTENT_SUCCESS_ADDED_PRODUCT = "success"
        const val INTENT_NEW_ADDRESS_ADD = "add"


        const val INTENT_BOOKING_LIST = "booking_list"

        const val INTENT_ADD_ANOTHER = "booking"

        const val INTENT_ADDR_LIST = "getAddID"


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

        const val BOOKING_SUMMERY_KEY ="orders_id"

        // acceptnace delete
        const val AGENT_ACCEPTANCE = 8
        const val MERCHNAT_ACCEPTANCE = 9

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


        const val OK = 1
        const val CANCEL =0

        const val delete =0
        const val edit = 1
        const val add =2
        const val VIEW_ALL =3

        // request code for activiity
        const val MERCHANT_HOME_FRAGMENT_REQUEST_CODE = 100
        const val ADDRESS_REQUECT_CODE =200

        //Dollor
        const val DOLLAR_NOTATION = "$"

        //Address Type
        const val Address_Home= "Home"
        const val Address_Work= "Work"
        const val Address_Other= "Other"

    }
}