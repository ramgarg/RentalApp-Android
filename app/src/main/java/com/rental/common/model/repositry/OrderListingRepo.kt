package com.rental.common.model.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.common.model.modelclass.Customer_detail
import com.rental.common.model.modelclass.OrderListing
import com.rental.common.model.modelclass.Order_listing
import com.rental.common.model.modelclass.Product_detail
import com.rental.webservice.APIServices
import com.rental.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class OrderListingRepo() {

    var apiServices: APIServices

    init {
        apiServices = RetrofitInstance.client.create(APIServices::class.java)

    }

    fun getOrdrListing():LiveData<OrderListing> {

        val data: MutableLiveData<OrderListing> = MutableLiveData<OrderListing>()

        /*val call = apiServices?.getOrderListing()

        call?.enqueue(object : retrofit2.Callback<OrderListing> {
            override fun onFailure(call: Call<OrderListing>, t: Throwable) {

                data.value=null
            }

            override fun onResponse(call: Call<OrderListing>, response: Response<OrderListing>) {

                data.value=response.body()
            }


        })*/

        // hard code value
        val Order_listing:Order_listing = Order_listing(Customer_detail("sunil",123,"959938")
        , Product_detail("","",3,3.2,"",
                "testing","20-Jun-2020"),12,"232232","sucess"
        )
       /* val Order_listing2:Order_listing = Order_listing(Customer_detail("sunil",123,"959938")
            , Product_detail("","",3,3.2,"",
                "testing","20-Jun-2020"),12,"232232","sucess"
        )
        val Order_listing3:Order_listing = Order_listing(Customer_detail("sunil",123,"959938")
            , Product_detail("","",3,3.2,"",
                "testing","20-Jun-2020"),12,"232232","sucess"
        )*/

        val orderListing = OrderListing(ArrayList<Order_listing>())
        var i= 0
        while (i<5){
          orderListing.order_listing.add(Order_listing)
            i++;
        }

        data.value = orderListing
        return data

    }


}