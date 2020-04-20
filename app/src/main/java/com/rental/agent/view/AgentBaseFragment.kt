package com.rental.agent.view

import android.app.Activity
import androidx.fragment.app.Fragment
import com.rental.common.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.activity_agent_home_.*

open class AgentBaseFragment : BaseFragment() {

    fun setDrawerVisibility(visibility:Int,activity: Activity){
        activity.drawer_layout_agent.visibility = visibility
    }
}