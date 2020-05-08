package com.eazyrento

interface ValidationMessage{
    companion object{
        const val SOCIAL_FAILED = "Auth failed.Please try after some time."
        const val CHECK_INTERNET = "Please check internet connectivity"
        const val VALID_PASSWORD_LENGTH= "Invalid Password! minimum length 8"
        const val VALID_PASSWORD= "Please Enter Valid Password"
        const val VALID_EMAIL_ID = "Please Enter Valid Email"
        const val ENTER_ALL_FIELDS = "Please Enter all fields"
        const val CHECK_TERMS_POLICY= "Please Check Terms and Condition"
        const val VALID_USER_NAME= "Please Enter User Name Field"
        const val SELECT_USER_ROLE = "Please select user role"
        const val NO_DATA_FOUND = "No data found"
        const val VALID_OTD="Please Enter Valid OTP"
        const val OTP_SENT="OTP have been sent"

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
        const val REQUEST_SUCCESSED = "Request has been submit successfully"
        const val PRODUCT_DELETE_SUCCESS = "Product has been deleted successfully"

        //Booking details validation
        const val START_DATE = "Please select start date"
        const val START_TIME = "Please select start time"
        const val END_DATE = "Please select end date"
        const val END_TIME = "Please select end time"

        const val DATE_VALIDATION = "Start date can not be less then end date"
        const val SELECT_ADRESS = "Please select address"

        const val BOOKING_SUBMITTED = "Thank you for submitting your order.Our agent will call you shortly."

        //agent note
        const val UNDER_DEVELOPMENT = "Under Development"
        const val VALID_PROFILE_NAME = "Enter your name"
        const val VALID_IMAGE = "Add profile photo"
        const val VALID_HEADING = "Write a heading"
        const val VALID_DESC = "Write description"
        const val VALID_ADDRESS = "Enter Your Address"
        const val VALID_ADDRESS_TYPE = "Enter Address Type"
        const val ADDRESS_ADDED = "Address Added Successfully"
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
        const val PHONE_NUMBER="Enter phone number"
        const val COUNTRY_CODE="Enter country code"
        const val DATE_OF_BIRTH="Enter date of birth"
        const val GENDER="Select Gender"
        const val DOCUMENT="Select Document"
        const val COMPANY="Enter Company"


    }
}