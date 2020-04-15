package io.covid19.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.covid19.core.utils.Activities
import io.covid19.core.utils.intentTo

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(intentTo(Activities.Home))
        finish()
    }
}
