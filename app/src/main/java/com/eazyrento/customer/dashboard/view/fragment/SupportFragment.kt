package com.eazyrento.customer.dashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eazyrento.Env
import com.eazyrento.R
import com.eazyrento.customer.dashboard.view.activity.CustomerMainActivity
import com.fugu.FuguConfig

class SupportFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        val view = inflater.inflate(R.layout.fragment_hippo_chat_lyt, container, false)
       // (activity as CustomerMainActivity).layout_loading.visibility=View.GONE
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showConversation(null)
    }

    fun showConversation(view: View?){

        FuguConfig.getInstance().showConversations(requireActivity(),Env.HIPPO_CHAT_TITLE)
    }

}
