package io.covid19.core.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    protected open var enableInjection = true

    override fun onCreate(savedInstanceState: Bundle?) {
        if (enableInjection) {
            AndroidInjection.inject(this)
        }
        super.onCreate(savedInstanceState)
    }
}