package io.covid19.core.adapters

import androidx.databinding.ViewDataBinding

abstract class BaseBindingViewHolder<I>(
    val viewDataBinding: ViewDataBinding
) : BaseViewHolder<I>(viewDataBinding.root) {

    @Suppress("UNCHECKED_CAST")
    inline fun <T : ViewDataBinding> bind(binding: T.() -> Unit) {
        binding(viewDataBinding as T)
        viewDataBinding.executePendingBindings()
    }
}