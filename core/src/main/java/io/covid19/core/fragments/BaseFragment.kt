package io.covid19.core.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    protected open var enableInjection = true

    protected abstract val layoutId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (enableInjection) {
            AndroidSupportInjection.inject(this)
        }
    }
}