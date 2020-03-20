package io.covid19.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class BaseAdapter<I, VH : BaseViewHolder<I>>(
    var items: MutableList<I> = mutableListOf()
) : RecyclerView.Adapter<VH>() {

    private var onItemClickListener: ((item: I?, position: Int) -> Unit)? = null

    protected lateinit var context: Context
    protected var recyclerView: RecyclerView? = null
    protected var inflater: LayoutInflater by Delegates.notNull()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        this.context = recyclerView.context
        inflater = LayoutInflater.from(context)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun getItemCount() = items.size

    fun setOnItemClickListener(onItemClickListener: ((item: I?, position: Int) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        initOnItemClickListener(holder, position)

        holder.bindItem(items[position], position)
    }

    open fun bind(holder: VH, position: Int) {
        holder.bindItem(items.getOrNull(position), position)
    }

    private fun initOnItemClickListener(holder: VH, position: Int) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(items[position], position)
            }
        }
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH

    open fun add(item: I) {
        items.add(item)
        notifyItemInserted(items.size)
    }

    open fun add(items: MutableList<I>) {
        if ((this.items.size) == 0) {
            submitList(items)
            return
        }

        val oldSize = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(oldSize, items.size)
    }

    fun submitList(items: MutableList<I>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun get(position: Int): I? {
        return items[position]
    }
}