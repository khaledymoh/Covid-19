package io.covid19.core.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.changeFragment(
    fragment: Fragment,
    layoutId: Int,
    tagFragmentName: String,
    bundle: Bundle = Bundle()
) {

    val fragmentManager = this.supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

    val currentFragment = fragmentManager.primaryNavigationFragment
    if (currentFragment != null) {
        fragmentTransaction.hide(currentFragment)
    }

    var fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName)
    if (fragmentTemp == null) {
        fragment.arguments = bundle
        fragmentTemp = fragment
        fragmentTransaction.add(layoutId, fragmentTemp, tagFragmentName)
    } else {
        fragmentTransaction.show(fragmentTemp)
    }

    fragmentTransaction.apply {
        setPrimaryNavigationFragment(fragmentTemp)
        setReorderingAllowed(true)
        commitAllowingStateLoss()
    }
}