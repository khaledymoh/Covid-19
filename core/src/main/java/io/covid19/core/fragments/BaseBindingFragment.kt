package io.covid19.core.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingFragment<T : ViewDataBinding> : BaseFragment() {

    protected var binding: T? = null
    protected var container: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding?.root
    }

    @Suppress("UNCHECKED_CAST")
    fun bind(binding: T?.() -> Unit) {
        binding(this.binding)
        this.binding?.executePendingBindings()
    }
}