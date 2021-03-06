package com.eazyrento.webservice

import com.eazyrento.Env

class PathURL {


    companion object {

        const val BASE_URL = Env.BASE_URL

        const val DASHBOARD = "users?page=2"



        //Login
        //Login Module
        const val REGISTER       = "/user/register"
        const val LOGIN          = "/user/login"
        const val VERIFY_PASSCODE = "/user/verify_passcode"
        const val USER_PROFILE    = "/user/profile"
        const val RESEND_OTP      = "/user/resend_otp"

        const val FORGOT_PASSWORD = "/user/forget_password"
        const val RESET_PASSWORD  = "/user/reset_password"

        const val UserAddNotes      = "/user/notes/create"
        const val UserNotesList     = "/user/notes/list"
        const val UserUpdateNote    = "/user/notes/update/{id}"
        const val UserDeleteNote    = "/user/notes/delete/{id}"

        //notification list
        const val NOTIFICATION = "/user/notification/list"
        // delete notification
        const val DELETE_NOTIFICATION = "/user/notification/delete"
        const val READ_NOTIFICATION   = "/user/notification/read"

        // user count wishlist and notification
        const val USER_DASHBOARD = "/user/dashboard"

        // Common
        const val MasterCategory      = "/product/masters"
        const val ProductCategory     = "/product/{master_name}/categories"
        const val ProductSubCategory  = "/product/{category_name}/subcategories"
        const val Product             = "/product/{sub_category_name}/products"
        const val ProductDetail       = "product/product_detail/{id}"

        //Customer
        const val CreateBooking       = "/customer/create_booking"
        const val AddToWishlist       = "/customer/add_to_wishlist"
        const val GetWishlist         = "/customer/wishlist"
        const val DeleteWishlist      = "/customer/delete_wishlist/{id}"

        const val CustomnerOrders       = "/customer/orders/{value}"
        const val CustomnerOrderDetail  = "/customer/order_detail/{id}"

        const val CustomerProductUnavailable = "/customer/customer_product_unavailable"

        const val CUSTOMER_APPLY_PROMO = "customer/apply_promo"

        const val MakePayment      = "/customer/payment/{id}"
        const val PaymentList      = "/customer/payments"
        const val PaymentListByID      = "/customer/payments/{id}"

        // payment getway
        const val PAYMENT_GETWAY_CHECKOUT_ID     = "/customer/hyperpay_checkout/{order_id}"
        const val PAYMENT_GETWAY_URL     = "/web/customer/payment_form/mobile/"
        const val PAYMENT_GETWAY_CALLBACK_URL = "/customer/hyperpay_callback/"

        const val CustomerFeedback = "/customer/feedback"

        const val CUSTOMER_MY_BOOKINGS = "/customer/bookings"
        
        
        //Merchant
        const val MerchantDashboard       = "/merchant/merchant_dashboard"
        const val MerchantProductCategory = "/merchant/products"
        const val MerchantProducts        = "/merchant/products/"
        const val MerchantDeleteProduct           = "/merchant/product_detail/{id}"

        const val MerchantProductDetail   = "/merchant/product_detail/{id}"
        const val MerchantAddProduct   = "/merchant/add_product"
        const val MerchantNotifyAdmin  = "/merchant/merchant_product_suggestion"
        const val MerchantUpdateProduct = "/merchant/product_detail/{id}"
        const val MerchantOrders       = "/merchant/orders/{value}"
        const val MerchantOrderDetail  = "/merchant/order_detail/{id}"
        const val MerchantFeedback     = "/merchant/feedback"
        const val MERCHANT_ACCEPTANCE_DECLINE = "/merchant/booking_action"

        const val DYNAMIC_FEEDBACK = "/{name}/feedback"


        //agent module
        const val AgentDashboard                = "/agent/agent_dashboard"
        const val AgentMyBookings               = "/agent/bookings"
        const val AgentBookingDetail            = "/agent/booking_detail/{id}"

//        const val AgentPendingOrdersAcceptance  = "/agent/booking_action"
        const val AgentOrders                   = "/agent/orders/{value}"
        const val AgentOrderDetail              = "/agent/order_detail/{id}"
        const val AgentSubOrderDetail           = "/agent/sub_orders/{sub_order_id}"
        const val AgentMerchants                = "/agent/find_nearby_merchants/{id}"
        const val AssignMerchants               = "/agent/assign_merchant"
        const val AgentRequestPayment           = "/agent/request_payment"
        const val AgentFeedback                 = "/agent/feedback"
        const val AgentPayments                 = "/agent/payments/{id}"
        const val AgentCollectCashPayment       = "/agent/collect_cash_payment"

        const val AGENT_ACCEPTANCE_DECLINE =      "/agent/booking_action"


        //address
        const val ADDRESS_LIST = "user/address/list"
        const val ADDRESS_CREATE = "user/address/create"
        const val ADDRESS_UPDATE = "user/address/update/{id}"
        const val ADDRESS_DETAIL = "user/address/get/{id}"
        const val ADDRESS_DELETE = "user/address/delete/{id}"

        //language
        const val  LANGUAGE_CHANGE= "user/set_device_language"

        //Terms and Condition
        const val TERMS_AND_CONDITION = "/web/static-content/terms-and-conditions/"
        const val PRIVACY_POLICY = "/web/static-content/privacy-policy/"
        const val ABOUT_US = "/web/static-content/about-us/"

        //https://qa.eazyrento.com/privacy-policy/

        // assign driver list......

        //  show near by driver to agent
        const val AGENT_SHOW_NEAR_BY_DRIVERS = "agent/show_nearbuy_drivers/{booking_id}"

        //  show near by driver to Customer
        const val CUSTOMER_SHOW_NEAR_BY_DRIVERS = "customer/nearbuy_drivers"

        //  Customer order tracking
        const val CUSTOMER_ORDERS_TRACKING = "customer/track_order_drivers/{order_id}"

        //  merchant and agent Order tracking
        const val ORDERS_TRACKING = "order/tracking/{order_id}/{sub_order_id}"
        //  show Drivers to merchants
        const val MERCHANTS_DRIVER = "merchant/drivers"

        //  show Drivers to merchants
        const val MERCHANTS_ASSIGN_DRIVER = "merchant/assign_driver"


    }
}