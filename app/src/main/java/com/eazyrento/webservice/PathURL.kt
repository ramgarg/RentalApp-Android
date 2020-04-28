package com.eazyrento.webservice

import com.eazyrento.Env

class PathURL {


    companion object {

        const val BASE_URL = Env.BASE_URL

        const val DASHBOARD = "users?page=2"
        const val NOTIFICATION = "users?page=2"

        //Login
        //Login Module
        const val REGISTER       = "/user/register"
        const val LOGIN          = "/user/login"
        const val VERIFY_PASSCODE = "/user/verify_passcode"
        const val USER_PROFILE    = "/user/profile"
        const val RESEND_OTP      = "/user/resend_otp"

        const val FORGOT_PASSWORD = "/user/forget_password"
        const val RESET_PASSWORD  = "/user/reset_password"

        // Common
        const val MasterCategory      = "/product/masters"
        const val ProductCategory     = "/product/{master_name}/categories"
        const val ProductSubCategory  = "/product/{category_name}/subcategories"
        const val Product             = "/product/"
        const val ProductDetail       = "product/product_detail/{id}"

        //Customer
        const val CreateBooking       = "/customer/create_booking"
        const val AddToWishlist       = "/customer/add_to_wishlist"
        const val GetWishlist         = "/customer/wishlist"
        const val DeleteWishlist      = "/customer/delete_wishlist/{id}"

        const val CustomnerOrders       = "/customer/orders/{value}"
        const val CustomnerOrderDetail  = "/customer/order_detail/{id}"

        const val CustomerProductUnavailable = "/customer/customer_product_unavailable"

        const val MakePayment      = "/customer/payment/"
        const val PaymentList      = "/customer/payments"
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
        const val MerchantUpdateProduct = "/merchant/product_detail/"
        const val MerchantOrders       = "/merchant/orders/"
        const val MerchantOrderDetail  = "/merchant/order_detail/"
        const val MerchantFeedback     = "/merchant/feedback"


        //agent module
        const val AgentDashboard                = "/agent/agent_dashboard"
        const val AgentMyBookings               = "/agent/bookings"
        const val AgentBookingDetail            = "/agent/booking_detail/"

        const val AgentPendingOrdersAcceptance  = "/agent/booking_action"
        const val AgentOrders                   = "/agent/orders/"
        const val AgentOrderDetail              = "/agent/order_detail/"
        const val AgentUpdateOrder              = "/agent/order_detail/"
        const val AgentMerchants                = "/agent/find_nearby_merchants/"
        const val AssignMerchants               = "/agent/assign_merchant"
        const val AgentRequestPayment           = "/agent/request_payment"
        const val AgentFeedback                 = "/agent/feedback"
        const val AgentPayments                 = "/agent/payments/"
        const val AgentCollectCashPayment       = "/agent/collect_cash_payment"


        //address
        const val ADDRESS_LIST = "user/address/list"

    }
}