package io.covid19.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.covid19.countries.R
import io.covid19.countries.databinding.BottomSheetCountryDetailsBinding
import io.covid19.data.models.Country

class CountryDetailsBottomSheet(private val country: Country) : BottomSheetDialogFragment() {

    private var binding: BottomSheetCountryDetailsBinding? = null

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_country_details,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(country)
    }

    private fun bind(country: Country) {
        binding?.country = country
        binding?.executePendingBindings()
    }

}
