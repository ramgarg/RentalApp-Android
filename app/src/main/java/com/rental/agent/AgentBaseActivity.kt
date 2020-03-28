package com.rental.agent

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.rental.agent.view.BottomNavigationActivity

open abstract class AgentBaseActivity : BottomNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}