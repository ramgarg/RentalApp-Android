package com.rental

interface Constant{
    companion object
    {
        const val  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1234
        //SECOND
        const val API_CONNECTION_TIMEOUT = 6*1000L
        const val API_READ_TIMEOUT = 6*1000L

        // key name
        const val MASTER_DATA_ITEM ="master_data_item"
        const val VEHICLES_DATA_ITEM ="vehicles"
        const val VEHICLES_SUB_CATE = "vehicle_sub"
    }
}