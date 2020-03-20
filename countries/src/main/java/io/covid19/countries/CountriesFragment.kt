package io.covid19.countries

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import io.covid19.core.fragments.BaseBindingFragment
import io.covid19.core.utils.hide
import io.covid19.core.utils.observe
import io.covid19.core.utils.show
import io.covid19.countries.databinding.FragmentCountriesBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCountriesRecyclerViewAdapter()
        statisticsObserver()
        initSearchEditText()
        initSwipeRefreshLayoutListener()
    }

    private fun initSwipeRefreshLayoutListener() {
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            viewModel.executeRequestStatistics()
        }
    }

    private fun setupCountriesRecyclerViewAdapter() {
        binding?.recyclerViewCountries?.adapter = countryAdapter
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

    private fun handelStatisticsSuccess(data: StatisticsWrapper) {
        bind { this?.statistics = data }
        countryAdapter.submitList(data.notEmptyCountries())
        countryAdapter.filter.filter("")
    }

    private fun handelStatisticsLoading(loading: Result.Loading<*>) {
        if (loading.show) {
            binding?.errorView?.hide()
            binding?.progressBar.show()
            binding?.linearLayoutContentContainer.hide()
        } else {
            binding?.progressBar.hide()
            binding?.linearLayoutContentContainer.show()
            binding?.swipeRefreshLayout?.isRefreshing = false
        }
    }

    private fun handelStatisticsError(error: Result.Error<*>) {
        binding?.linearLayoutContentContainer.hide()
        binding?.errorView?.errorMessage(error.error.messageResource)
        binding?.errorView?.setOnRetryClickListener {
            viewModel.executeRequestStatistics()
        }
    }

    private fun initSearchEditText() {
        binding?.editTextCountriesFragmentSearch?.doOnTextChanged { text, _, _, _ ->
            countryAdapter.filter.filter(text)
        }
    }
}
