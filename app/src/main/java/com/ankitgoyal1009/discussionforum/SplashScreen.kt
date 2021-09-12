package com.ankitgoyal1009.discussionforum

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ankitgoyal1009.discussionforum.discussions.ui.DiscussionsActivity
import com.ankitgoyal1009.discussionforum.login.LoginViewModel
import com.ankitgoyal1009.discussionforum.login.ui.LoginActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val model = ViewModelProvider(this).get(LoginViewModel::class.java)

        Handler().postDelayed({
            if (!model.isUserLoggedIn()) {
                startLoginActivity()
            } else {
                DiscussionsActivity.startActivity(this)
            }
            this@SplashScreen.finish()
        }, 2000)
    }

    fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
