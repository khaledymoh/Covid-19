package io.covid19.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import io.covid19.core.adapters.BaseAdapter
import io.covid19.core.adapters.BaseBindingViewHolder
import io.covid19.countries.databinding.RowCountryBinding
import io.covid19.data.models.Country

class CountryAdapter : BaseAdapter<Country, CountryAdapter.CountryViewHolder>(), Filterable {

    private var filteredCountries = items

    override fun getViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            RowCountryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindItem(filteredCountries[position], position)
        initOnItemClickListener(holder, position, filteredCountries)
    }

    override fun getItemCount() = filteredCountries.size

    override fun getFilter() = CountryFilter()

    inner class CountryFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredCountries = if (constraint.toString().isNotEmpty()) {
                items.filter {
                    it.countryName?.toLowerCase()?.contains(
                        constraint.toString().toLowerCase()
                    ) == true
                }.toMutableList()
            } else {
                items
            }
            val filterResult = FilterResults()
            filterResult.values = filteredCountries
            return filterResult
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredCountries = results?.values as MutableList<Country>
            notifyDataSetChanged()
        }
    }

    inner class CountryViewHolder(
        private val viewBinding: RowCountryBinding
    ) : BaseBindingViewHolder<Country>(viewBinding) {

        override fun bindItem(item: Country?, position: Int) {
            bind<RowCountryBinding> {
                this.country = item
            }

            handleContainerBackground()
        }

        private fun handleContainerBackground() {
            if (adapterPosition.rem(2) == 0) {
                viewBinding.linearLayoutRowCountryContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorRowDivider
                    )
                )
            } else {
                viewBinding.linearLayoutRowCountryContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.viewBackground
                    )
                )
            }
        }
    }
}