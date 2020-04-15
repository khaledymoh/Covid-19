package io.covid19.countries

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.children
import androidx.core.view.get
import com.google.android.material.chip.Chip
import com.google.android.material.textview.MaterialTextView
import io.covid19.core.fragments.BaseBindingFragment
import io.covid19.core.utils.*
import io.covid19.countries.databinding.FragmentCountriesBinding
import io.covid19.countries.details.CountryDetailsBottomSheet
import io.covid19.data.enums.CountryFilterType
import io.covid19.data.models.StatisticsWrapper
import io.covid19.data.network.Result
import io.covid19.statistics.StatisticsViewModel
import javax.inject.Inject

class CountriesFragment : BaseBindingFragment<FragmentCountriesBinding>() {

    override val layoutId: Int = R.layout.fragment_countries

    @Inject
    lateinit var viewModel: StatisticsViewModel

    @Inject
    lateinit var countryAdapter: CountryAdapter

    private var headerIcons = mutableListOf<AppCompatImageView>()

    private val tableHeaderTextsClickListener = View.OnClickListener { view ->
        view?.tag?.let { tag ->
            countryAdapter.sortByTag(tag.toString())
            resetHeaderIcons()
            applySelectionTo(tag.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCountriesRecyclerViewAdapter()
        statisticsObserver()
        initSearchViewObserver()
        findHeaderIcons(view as ViewGroup)
        initContinentsChipGroupCheckedListener()
    }

    private fun setupCountriesRecyclerViewAdapter() {
        binding?.recyclerViewCountries?.adapter = countryAdapter
        countryAdapter.setOnItemClickListener { item, _ ->
            item?.let {
                CountryDetailsBottomSheet(it).show(
                    childFragmentManager,
                    this::class.java.simpleName
                )
            }
        }
    }

    private fun setupContinentsChipGroup(continents: MutableList<String>) {
        continents.forEach {
            binding?.chipGroupCountriesFragmentContinents?.addView(getChipView(it))
        }
        (binding?.chipGroupCountriesFragmentContinents?.get(FIRST_CHIP_INDEX) as Chip).isChecked =
            true
    }

    private fun initContinentsChipGroupCheckedListener() {
        binding?.chipGroupCountriesFragmentContinents?.setOnCheckedChangeListener { _, _ ->
            binding?.chipGroupCountriesFragmentContinents?.children?.forEach { view ->
                val chip = view as Chip
                if (chip.isChecked) {
                    if (chip.text.toString() == viewModel.getStatisticsWrapper().continents.first()) {
                        countryAdapter.filterBy(CountryFilterType.COUNTRY, "")
                    } else {
                        countryAdapter.filterBy(CountryFilterType.CONTINENT, chip.text.toString())
                    }
                }
            }
        }
    }

    private fun getChipView(text: String): Chip {
        val chip = layoutInflater.inflate(R.layout.view_chip_continent, container, false) as Chip
        chip.text = text
        return chip
    }

    private fun statisticsObserver() {
        viewModel.getStatisticsLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    handelStatisticsSuccess(result.data as StatisticsWrapper)
                }
                is Result.Loading -> {
                    handelStatisticsLoading(result)
                }
                is Result.Error -> {
                    handelStatisticsError(result)
                }
            }
        }
    }

    private fun resetHeaderIcons() {
        headerIcons.forEach {
            it.setTint(R.color.textSecondary)
            it.smoothRotate(0f)
        }
    }

    private fun handelStatisticsSuccess(data: StatisticsWrapper) {
        bind { this?.statistics = data }
        setupContinentsChipGroup(data.continents)
        countryAdapter.submitList(data.excludedCountries())
        countryAdapter.filterBy(CountryFilterType.COUNTRY, "")
    }

    private fun handelStatisticsLoading(loading: Result.Loading<*>) {
        if (loading.show) {
            binding?.errorView?.hide()
            binding?.progressBar.show()
            binding?.horizontalScrollViewCountriesFragment.hide()
        } else {
            binding?.progressBar.hide()
            binding?.horizontalScrollViewCountriesFragment.show()
        }
    }

    private fun handelStatisticsError(error: Result.Error<*>) {
        binding?.horizontalScrollViewCountriesFragment.hide()
        binding?.errorView?.errorMessage(error.error.messageResource)
        binding?.errorView?.setOnRetryClickListener {
            viewModel.executeRequestStatistics()
        }
    }

    private fun initSearchViewObserver() {
        viewModel.getSearchLiveData().observe(viewLifecycleOwner) {
            countryAdapter.filterBy(CountryFilterType.COUNTRY, it)
        }
    }

    private fun applySelectionTo(tag: String) {
        headerIcons.first { it.tag?.toString() == tag }.apply {
            setTint(R.color.colorAccent)
            smoothRotate(if (CountriesItemsFactory.isRotated) ROTATION_DEGREE else DEFAULT_ROTATION_DEGREE)
        }
    }

    private fun findHeaderIcons(viewGroup: ViewGroup) {
        for (i in 0 until viewGroup.childCount) {
            when (val v = viewGroup.getChildAt(i)) {
                is ViewGroup -> findHeaderIcons(v)
                is AppCompatImageView -> headerIcons.add(v)
                is MaterialTextView -> v.setOnClickListener(tableHeaderTextsClickListener)
            }
        }
    }

    private companion object {

        private const val DEFAULT_ROTATION_DEGREE = 0f
        private const val ROTATION_DEGREE = 180f
        private const val FIRST_CHIP_INDEX = 0
    }
}
