package com.rental.webservice

class Constant {


    companion object {

        const val BASE_URL = "https://reqres.in/api/"
//        const val BASE_URL = "http://qa.eazyrento.com:8000"

        const val LOGIN = ""

        const val DASHBOARD = "users?page=2"
        const val NOTIFICATION = "users?page=2"
        const val REGISTER = ""
        const val FORGOTPASSOWRD = ""
        const val SENDOTP = ""
        const val ORDER_SUMMARY = ""
        const val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234
        
        //Login
        //Login Module
        const val Register       = "/user/register"
        const val VerifyPasscode = "/user/verify_passcode"
        const val UserProfile    = "/user/profile"
        const val ResendOTP      = "/user/resend_otp"
        const val Login          = "/user/login"
        const val ForgotPassword = "/user/forget_password"
        const val ResetPassword  = "/user/reset_password"

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