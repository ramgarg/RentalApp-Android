package com.rental.webservice

class Constant {


    companion object {

        const val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234

//        const val BASE_URL = "https://reqres.in/api/"
        const val BASE_URL = "http://qa.eazyrento.com:8000"

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
        const val Category            = "/product/"
        const val SubCategory         = "/product/"
        const val Product             = "/product/"
        const val ProductDetail       = "/product/product_detail/"

        //Customer
        const val CreateBooking       = "/customer/create_booking"
        const val AddToWishlist       = "/customer/add_to_wishlist"
        const val GetWishlist         = "/customer/wishlist"
        const val DeleteWishlist      = "/customer/delete_wishlist/"

        const val CustomnerOrders       = "/customer/orders/"
        const val CustomnerOrderDetail  = "/customer/order_detail/"

        const val CustomerProductUNavailable = "/customer/customer_product_unavailable"

        const val MakePayment      = "/customer/payment/"
        const val PaymentList      = "/customer/payments"
        const val CustomerFeedback = "/customer/feedback"

        //agent module
        const val ORDER_LISTING = "order_lisitng"

    }
}