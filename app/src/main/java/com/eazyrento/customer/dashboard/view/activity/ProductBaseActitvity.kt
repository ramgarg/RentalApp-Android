package com.eazyrento.customer.dashboard.view.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.eazyrento.common.model.modelclass.ProductSubCategoriesModelResItem
import com.eazyrento.common.model.modelclass.ProductCateItem
import com.eazyrento.R
import com.eazyrento.common.view.BaseActivity
import com.eazyrento.customer.dashboard.view.adapter.InfalterViewAdapter
import com.eazyrento.customer.dashboard.view.adapter.ProductVehiclesAdapter
import com.eazyrento.customer.utils.Common
import com.eazyrento.customer.utils.MoveToAnotherComponent
import com.eazyrento.customer.utils.ViewVisibility
import kotlinx.android.synthetic.main.category_activity.*
import kotlinx.android.synthetic.main.fragment_home.rec_veichle
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.math.roundToInt

open abstract class ProductBaseActitvity: BaseActivity(),
    InfalterViewAdapter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.category_activity)



    }
    protected fun setData(name:String){

        topBarWithBackIconAndTitle(name.capitalize())
        ed_search.hint = resources.getString(R.string.search_vehicles)+" "+name
    }

    protected fun <T>setSearch(arrayListOiginal: ArrayList<T>){

        var arrayListAfterSorting:ArrayList<T> = ArrayList()

        ed_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(s.isEmpty()) Common.hideSoftKeyBoard(this@ProductBaseActitvity,ed_search)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val textlength = ed_search.text.length
                arrayListAfterSorting.clear()
              // serching in list
                serchingInList(textlength,arrayListOiginal,arrayListAfterSorting)

                rec_veichle.adapter=
                    ProductVehiclesAdapter(
                        arrayListAfterSorting,
                        this@ProductBaseActitvity,
                        this@ProductBaseActitvity
                    )
0
                if(arrayListAfterSorting.size==0){
                    layout_vehicle_not_found.visibility= View.VISIBLE
                    sv.scrollTo(5, resources.getDimension(R.dimen._300sdp).roundToInt())
                    notify_admin.setOnClickListener {
                        MoveToAnotherComponent.moveToActivityNormal<NotifyToAdminActivity>(this@ProductBaseActitvity)
                    }
                }else
                    layout_vehicle_not_found.visibility= View.GONE
            }
        })
    }

    private fun <T>serchingInList(textlength:Int , arrayListOriginal:List<T>,arrayListSorting:ArrayList<T>) {



        for (i in 0 until arrayListOriginal.size) {
            var name:String=""
            arrayListOriginal[i].let { if(it is ProductCateItem){name = it.category_name}
                if(it is ProductSubCategoriesModelResItem){name = it.subcategory_name}
            }

            if (textlength <=name.length ) {
                if (name.toLowerCase().trim().contains(
                        ed_search.text.toString().toLowerCase().trim()
                    )) {
                    arrayListSorting.add(arrayListOriginal[i])
                }
            }
        }
    }

    override fun getInflaterViewIDAdapter(): Int {
        return R.layout.card_product_template
    }

    override fun <T> setListnerOnView(
        view: View?,
        type: T,
        where: Int,
        pos: Int
    ) {
    }


}

