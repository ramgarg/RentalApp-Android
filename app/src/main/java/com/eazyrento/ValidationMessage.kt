package com.eazyrento

import com.eazyrento.supporting.TimeConstant

interface ValidationMessage{
    companion object{
        const val ENTER_AMOUNT_PAYMENT = "Please enter the amount."
        const val PAYMENT_MODE = "Please chose the payment method."
        const val ENTER_AMOUNT_PAYMENT_LESS_THEN_TO = "Enter amount can not be grater then to total price"

        const val ADD_PRODUCT_AT_LEAST_ONE = "Product list can not be empty.Please add at list one product."
        const val PLAY_SERVICE_APK_NOT_FOUND= "Please install the Google play service APK to get accurate location."
        const val CURRENT_LOCATION_NOT_GETTING= "There are some issue to getting location.Please restart your device or launch the Google Map once to get the location."
        const val SOCIAL_FAILED = "Auth failed.Please try after some time."
        const val CHECK_INTERNET = "Please check internet connectivity"
        const val VALID_PASSWORD_LENGTH= "Invalid password! minimum length 8"
        const val VALID_PASSWORD= "Please enter Valid password"
        const val REGISTRATION_SUCCESS= "Registration successfull. Please login now to continue"
        const val VALID_EMAIL_ID = "Please enter valid email address"
        const val ENTER_ALL_FIELDS = "Please enter all fields"
        const val ENTER_EMAIL_PHONE= "Please enter email address or phone number"
        const val CHECK_TERMS_POLICY= "Please check terms and condition"
        const val VALID_USER_NAME= "Please enter user name"
        const val SELECT_USER_ROLE = "Please select user role"
        const val NO_DATA_FOUND = "No data found"
        const val VALID_OTD="Please enter valid OTP"
        const val OTP_SENT="OTP have been sent"
        const val FORGOT_PASSWORD="Password reset link is sent over mail"
        const val OTP_MESSAGE_START= "We've sent OTP to the email"
        const val OTP_MESSAGE_END= "Please enter it below to complete verification."
        const val PROFILE_UPDATE="Profile has been updated successfully!"

        //merchant add product validation
        const val DOC_IS_NOT_UPLOADED = "Doc is not uploaded.Please try it again."
        const val SELECT_AT_LIST_ONE_DAYS = "Please select at list one day."
        const val FILL_QUANTITY = "Please fill quantity."
        const val FILL_BOOKING_PRICE ="Booking price can not be zero or empty."
        const val SELECT_FUEL_TYPE_SPINNER = "Please select fuel type."
        const val SELECT_DOCUMENT = "Please select document."
        const val UPLOAD_DOCUMENT = "Please upload a document."
        //merchnat product submit
        const val ON_SUBMIT_BUTTON_CLICK ="Do you want to submit the product info?"
        const val SUBMIT_TITLE ="Submit"

        //logout

        const val LOGOUT_MESSAGE = "You have been logout successfully"
        //sucessfully added

        const val OPT_SUCCESSED = "OTP has sent.Please check your email to verify."

        const val REQUEST_SUCCESSED = "Request has been submit successfully"
        const val PRODUCT_DELETE_SUCCESS = "Product has been deleted successfully"

        //Booking details validation
        const val START_DATE = "Please select start date"
        const val START_TIME = "Please select start time"
        const val END_DATE = "Please select end date"
        const val END_TIME = "Please select end time"

        const val DATE_VALIDATION = "Start date can not be less then end date"
        const val SAME_DATE_TIME_VALIDATION = "The time gap between start time and end time should be more then "+TimeConstant.TIME_GAP_BETWEEN_SAME_DATE+" hour for same date"
        const val SELECT_ADRESS = "Please select address"

        const val BOOKING_SUBMITTED = "Thank you for submitting. We will contact you shortly."

        //agent note
        const val UNDER_DEVELOPMENT = "Under Development"
        const val VALID_PROFILE_NAME = "Enter your name"
        const val VALID_IMAGE = "Add profile photo"
        const val VALID_HEADING = "Write a heading"
        const val VALID_DESC = "Write description"
        const val VALID_ADDRESS = "Enter Your Address"
        const val VALID_ADDRESS_TYPE = "Select Address as Type"
        const val ADDRESS_ADDED = "Address has been saved successfully!"
         const val LOCATION ="Turn Your Location On"
        const val ITEM_IS_IN_LIST = "The product is already in list"

        const val SELECT_MERCHANT = "Please select at list one merchant"

        const val OTP_VALID_NUMBER = "Please enter valid number."

        //Merchant Notify Admin
        const val VALID_NAME = "Enter product name"
        const val VALID_POWER = "Enter product power"
        const val VALID_PRICE = "Enter product price"
        const val VALID_FUEL_TYPE = "Enter fuel type"
        const val VALID_CAPACITY ="Enter capacity"
        const val ADMIN_NOTIFY ="Request has been successfully submitted to admin."
        const val VALID_RATING = "Enter your feedback and Rating"
        const val FEEDBACK_SENT = "Feedback Sent"
        const val PHONE_NUMBER="Phone number is not valid"
        const val COUNTRY_CODE="Enter country code"
        const val DATE_OF_BIRTH="Enter date of birth"
        const val GENDER="Select Gender"
        const val DOCUMENT="Select Document"
        const val COMPANY="Enter Company"
        const val DESCRIPTON="Enter Description"

        const val COUNTRY_CODE_VALIDATION="Please enter valid country code"

        const val NO_ADRESS_FOUND = "No address found.Please add new address."


    }
}