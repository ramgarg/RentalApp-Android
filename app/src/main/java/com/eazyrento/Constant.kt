package com.eazyrento

interface Constant{
    companion object
    {
        val key_error_message = "detail"

        const val PLATFORM = "android"
        const val INTENT_PAYMENT_SUCSESS = "payment_sucess"
        const val KEY_ORDER_DETAILS_ID ="order_details_key"
        const val REQUEST_CODE_FINISH_ORDER_DETAILS_ON_BACK = 7001
        const val INTENT_UPDATE_ORDER_AGENT ="agent_update_order"

        const val DEFAULT_VALUE =""

        const val KEY_PAYMENT_HISTORY_AGENT = "ORDER_ID_AGENT"
        const val KEY_PAYMENT_HISTORY_CUSTOMER = "ORDER_ID_CUSTOMER"

        const val REQUEST_CODE_FINISH_LOGIN_ON_BACK = 8001

        const val REQUEST_CODE_FINISH_FIRST_TIME_USER = 3001

        const val REQUEST_CODE_FILTER_ORDER_LIST = 1101

        const val VALUE_FINISH_FIRST_TIME_USER = 1011

        const val KEY_FINISH_FIRST_TIME_USER = "first_time"

        const val LOGIN_WITH_DEEPLINK ="deeplink"
        const val LOGIN_VALUE = 1

        const val RC_SIGN_IN_GOOGLE = 9001
        const val PICK_PHOTO_FOR_AVATAR = 100
        const val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234
        //SECOND
        const val API_CONNECTION_TIMEOUT = 6*1000L
        const val API_READ_TIMEOUT = 6*1000L

        // key name
        const val MASTER_DATA_ITEM ="master_data_item"
        const val INTENT_FILTER_LIST ="filter_list_item"
        const val INTENT_FILTER_APPLY ="filter_apply"
        const val VEHICLES_DATA_ITEM ="vehicles"
        const val VEHICLES_SUB_CATE = "vehicle_sub"
        const val KEY_ADDRESS ="addr_id"

        const val KEY_UPDATE_DELETE_CREATE_REQUEST ="addressmod"
        const val KEY_DATA_IS_CREATING="ceate"

        const val KEY_FROM_PROFILE="profile"
        const val FIRST_TIME_USER_LOGIN= 2123

        const val BOOKING_PRODECT_DETAILS = "ID"

        // near by driver
        const val KEY_INTENT_NEAR_BY_DRIVER= "nearbydriver"
        const val VALUE_INTENT_NEAR_BY_DRIVER = 10901

        //intent key,value
        const val KEY_INTENT_SUCCESS_ORDER_BOOKING = "success"
        const val VALUE_INTENT_SUCCESS_ORDER_BOOKING = 1

        const val INTENT_MERCHANT_PRODUCT_LIST = "list"
        const val INTENT_MERCHANT_PRODUCT_ADD = "add"
        const val INTENT_MERCHANT_PRODUCT_EDIT = "edit"
        const val INTENT_ADDRESS_EDIT = "edit"
        const val INTENT_NOTE_EDIT = "edit"
        const val INTENT_NOTE_DELETE = "delete"
        const val INTENT_SUCCESS_ADDED_PRODUCT = "success"
        const val INTENT_NEW_ADDRESS_ADD = "add"
        const val INTENT_RESET_SUCCESSFULY ="reset"
        const val INTENT_NOTE_ADDED ="sucess"
        const val INTENT_NOTE_UPDATED ="note_update"

        const val INTENT_RATE_REVIEWS ="sucess"

        const val INTENT_OTP_USER_ID ="user_id"
        const val INTENT_USER_EMAIL="user_email"
        const val INTENT_OTP_TO_LOGIN ="otp"


        const val INTENT_BOOKING_LIST = "booking_list"

        const val INTENT_ADD_ANOTHER = "booking"

        const val INTENT_ADDR_LIST = "getAddID"



        const val INTENT_UPDATE_DELETE_CREATE_REQUEST = 5001

        const val REQUEST_CODE_PROFILE_UPDATE =6001


        // Order status
        const val OPEN_ORDER = 1
        const val CLOSE_ORDER =3
        const val ORDER_SUMMERY_KEY ="orders_id"

        //Agent sub order
        const val INTENT_AGENT_SUB_ORDER = "sub_order"
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

        //payment_noti History
        const val PAYPAL ="paypal"
        const val CASH ="cash"

        // order/booking status
        const val PENDING="pending"
        const val COMPLETED="completed"
        const val RECEIVED="received"
        const val FAILED="failed"
        const val REJECTED = "rejected"
        const val ACCEPTED  = "accepted"
        const val STARTED   = "started"
        const val ONGOING   = "ongoing"


       // const val PENDING_AMOUNT="Pending Amount - "
        const val TOTAL_AMOUNT="Total Amount"
        const val BOOKING_PRICE="Booking Price"

        const val DOLLAR="$"
       // const val QUANTITY="Quantity - "
      //  const val ORDER_ID="Order ID - "
       // const val BOOKING_ID="Booking ID - "


        const val MERCHANT="Merchant"
        const val AGENT="Agent"
        const val CUSTOMER="Customer"
        const val DISTANCE="Distance"
        const val KM="km"


        const val OK = 1
        const val CANCEL =0

        const val delete =0
        const val edit = 1
        const val add =2
        const val VIEW_ALL =3
        const val LENGTH = 4

        // request code for activiity
        const val MERCHANT_HOME_FRAGMENT_REQUEST_CODE = 100
        const val ADDRESS_REQUECT_CODE =200

        //Dollor
        const val DOLLAR_NOTATION = "$"

        //Address Type
        const val Address_Home= "Home"
        const val Address_Work= "Work"
        const val Address_Other= "Other"

        //logunt
        const val INTENT_LOGOUT_KEY = "logout"
        const val LOGOUT_VALUE = 1001

        //notification
        const val DEEPLINK_VALUE = "deep_link"
        const val REQUEST_CODE_PENDING_INTENT = 2011

        const val USER_NEED_LOGIN = "User need to login"


    }
}