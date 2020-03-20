package io.covid19.core.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<I>(
    view: View
) : RecyclerView.ViewHolder(view) {


    abstract fun bindItem(item: I?, position: Int)

}