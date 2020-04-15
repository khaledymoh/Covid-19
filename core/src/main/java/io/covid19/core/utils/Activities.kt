package io.covid19.core.utils

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

fun Context.intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(addressableActivity.action).setPackage(packageName)
}

fun Fragment.intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(addressableActivity.action).setPackage(context?.packageName)
}

interface AddressableActivity {

    val action: String
}

object Activities {

    object Home : AddressableActivity {

        override val action: String = "io.covid19.home.HomeActivity"
    }
}