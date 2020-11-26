package com.eazyrento

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.eazyrento.login.view.LoginUserActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            fcmTokenByFirebaseInstanceId(this)
            startActivity(Intent(this, LoginUserActivity::class.java))

            // close this activity
            finish()
        }, Env.SPLASH_TIME_OUT)
    }
}