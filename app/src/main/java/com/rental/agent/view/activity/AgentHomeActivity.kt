package com.rental.agent.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.R
import com.rental.agent.AgentBaseActivity
import com.rental.agent.view.fragment.AgentHomeFragment

class AgentHomeActivity :AgentBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agent_home_)
        setDefaultFragment()
    }


    private fun setDefaultFragment(){
        val fragment = AgentHomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}