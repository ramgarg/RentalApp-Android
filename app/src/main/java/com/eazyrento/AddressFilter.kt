package com.eazyrento

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.eazyrento.login.model.modelclass.AddressInfo
import java.lang.Exception

class AddressFilter(private val context: Context,private val addressInfo: AddressInfo?) {

    fun addressFromLocation(lat:Double, lng:Double,maxResult:Int ): MutableList<Address> {
        return Geocoder(context).getFromLocation(lat,lng,maxResult)
    }

     fun getAddressByLocation(lat:Double,lng:Double,maxResult:Int) {

        val listOfAddress = addressFromLocation(lat,lng,maxResult)

        addressInfo?.let {
            try {

                it.latitude = lat
                it.longitude = lng

                it.address_line = listOfAddress[0].getAddressLine(0)

                splitAddress( listOfAddress[0])

            }catch (ex: Exception){
                ex.printStackTrace()
            }

        }

    }

    private fun splitAddress(address: Address) {
        try {

            addressInfo?.let {
                //it.appartment = address.subThoroughfare.plus(address.thoroughfare).plus(address.featureName)
                //it.city = address.subLocality.plus(address.locality)
                // it.state = address.subAdminArea.plus(address.adminArea)

                it.city = address.locality
                it.state = address.adminArea
                it.country = address.countryName
                it.appartment = address.featureName


            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}