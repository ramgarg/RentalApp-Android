package com.eazyrento

import com.eazyrento.supporting.TimeConstant

interface ValidationMessage{
    companion object{
        const val ORDER_UPDATE_BUTTON_CLICK_CONFIRM = "Before clicking update button please update any data."
        const val THANKYOU_FOR_CONFIRMING = "Thank you for confirming!"
        const val ENTER_AMOUNT_PAYMENT = "Please enter valid amount!"
        const val PAYMENT_MODE = "Please choose the payment method."
        const val ENTER_AMOUNT_PAYMENT_LESS_THEN_TO = "Please enter valid amount!"

        const val ADD_PRODUCT_AT_LEAST_ONE = "Product list is empty..."
        const val PLAY_SERVICE_APK_NOT_FOUND= "Please install the Google play service APK to get accurate location."
        const val CURRENT_LOCATION_NOT_GETTING= "There are some issue to getting location.Please restart your device or launch the Google Map once to get the location."
        const val SOCIAL_FAILED = "Auth failed.Please try after some time."
        const val CHECK_INTERNET = "Please check internet connectivity"
        const val VALID_PASSWORD_LENGTH= "Password must be between 8 and 15 characters."
        const val VALID_PASSWORD= "Please enter a valid password."
        const val REGISTRATION_SUCCESS= "Registration successful. Please Login now to continue."
        const val VALID_EMAIL_ID = "Please enter a valid email address."
        const val VALID_EMAIL_PHONE = "Please enter a valid email address or mobile number."
        const val ENTER_ALL_FIELDS = "Please enter all fields"
        const val CHECK_TERMS_POLICY= "Please agree to the terms and conditions."
        const val VALID_USER_NAME= "Please enter valid user name.It should be between 4 and 50 characters."
        const val SELECT_USER_ROLE = "Please select user role"
        const val NO_DATA_FOUND = "No Record Found..."
        const val VALID_OTD="Please enter valid OTP"
        const val OTP_SENT="OTP have been sent"
        const val FORGOT_PASSWORD="Password reset instruction has been sent to your email/mobile number."
        const val OTP_MESSAGE_START= "We've sent OTP to the email"
        const val OTP_MESSAGE_END= "Please enter it below to complete verification."
        const val PROFILE_UPDATE="Profile updated successfully!"

        //merchant add product validation
        const val DOC_IS_NOT_UPLOADED = "Doc is not uploaded.Please try it again."
        const val SELECT_AT_LIST_ONE_DAYS = "Please select at list one day."
        const val FILL_QUANTITY = "Assign quantity can't be zero."
        const val FILL_BOOKING_PRICE ="Please enter booking price."
        const val SELECT_FUEL_TYPE_SPINNER = "Please select fuel type."
        const val SELECT_DOCUMENT = "Please select document type."
        const val UPLOAD_DOCUMENT = "Please upload document."
        //merchnat product submit
        const val ON_SUBMIT_BUTTON_CLICK ="Do you want to submit the product info?"
        const val SUBMIT_TITLE ="Submit"

        //logout

        const val LOGOUT_MESSAGE = "You have been logout successfully"
        //sucessfully added

        const val OPT_SUCCESSED = "OTP has sent.Please check your email to verify."

        const val REQUEST_SUCCESSED = "Request has been submitted successfully!"
        const val PRODUCT_DELETE_SUCCESS = "Product has been deleted successfully!"

        //Booking details validation
        const val START_DATE = "Please add start date."
        const val START_TIME = "Please add start time."
        const val END_DATE = "Please add end date."
        const val END_TIME = "Please add end time."

        const val DATE_VALIDATION = "Please select valid start and end date time."
        const val SAME_DATE_TIME_VALIDATION = "The time gap between start time and end time should be more then "+TimeConstant.TIME_GAP_BETWEEN_SAME_DATE+" hour for same date"
        const val PREVIOSE_TIME_BUT_SAME_DATE_TIME = "Booking can not be accepted of same start date but time before the current time."

        const val SELECT_ADRESS = "Please select your address."

        const val BOOKING_SUBMITTED = "Thank you for submitting the order. Our agent will contact you shortly."

        //agent note
        const val UNDER_DEVELOPMENT = "Under Development"
        const val VALID_PROFILE_NAME = "Please enter your full name."
        const val VALID_IMAGE = "Please add your profile photo."
        const val VALID_HEADING = "Write a heading"
        const val VALID_DESC = "Write description"
        const val VALID_ADDRESS = "Please enter your Address Line."
        const val VALID_ADDRESS_TYPE = "Please select address as type"
        const val ADDRESS_ADDED = "Address has been saved successfully!"
         const val LOCATION ="Please turn your location on."
        const val ITEM_IS_IN_LIST = "The product is already in list."

        const val SELECT_MERCHANT = "Please assign merchant."

        const val OTP_VALID_NUMBER = "Please enter verification code."

        //Merchant Notify Admin
        const val VALID_NAME = "Please enter product name."
        const val VALID_POWER = "Please enter product power."
        const val VALID_PRICE = "Please enter product price."
        const val VALID_FUEL_TYPE = "Please enter fuel type."
        const val VALID_CAPACITY ="Please enter capacity."
        const val ADMIN_NOTIFY ="Detail has been sent to admin successfully!"
        const val VALID_RATING = "Please add some rating."
        const val FEEDBACK_SENT = "Thanks for rating. Your feedback is valuable for us!"
        const val PHONE_NUMBER="Please enter a valid mobile number."
        const val COUNTRY_CODE="Please enter country code."
        const val DATE_OF_BIRTH="Please enter your date of birth."
        const val GENDER="Please select your gender."
        const val DOCUMENT="Please select document type."
        const val COMPANY="Enter Company"
        const val DESCRIPTON="Enter Description"

        const val COUNTRY_CODE_VALIDATION="Please enter a valid dailing code."

        const val NO_ADRESS_FOUND = "No address found. Please add new address."

        const val QUANTITY_LIMIT = "Assign quantity can't be grater then or less then of booking quantity."

        const val QUANTITY_SET_LIMIT = "You can't set value beyond limit."

        const val USER_NEED_LOGIN = "User need to login"

        // filter validation and message
        const val SELECT_DATE = "Select order date"
        const val SELECT_STATUS = "Select order status"
        const val SELECT_ORDER_ID = "Select order ID"
        const val SELECT_PRODUCT_NAME = "Select product name"



    }
}