package com.eazyrento

interface ValidationMessage{
    companion object{
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

        //sucessfully added
        const val PRODUCT_ADDED_SUCCESS = "Request has been submit successfully"
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
        const val VALID_HEADING = "Write a heading"
        const val VALID_DESC = "Write description"
        const val VALID_ADDRESS = "Enter Your Address"
        const val VALID_ADDRESS_TYPE = "Enter Address Type"


    }
}