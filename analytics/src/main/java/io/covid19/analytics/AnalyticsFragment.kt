package io.covid19.analytics

import android.os.Bundle
import android.view.View
import io.covid19.analytics.databinding.FragmentAnalyticsBinding
import io.covid19.core.fragments.BaseBindingFragment
import io.covid19.core.utils.hide
import io.covid19.core.utils.observe
import io.covid19.core.utils.show
import io.covid19.data.models.StatisticsWrapper
import io.covid19.data.network.Result
import io.covid19.statistics.StatisticsViewModel
import javax.inject.Inject


class AnalyticsFragment : BaseBindingFragment<FragmentAnalyticsBinding>() {

    override val layoutId: Int = R.layout.fragment_analytics

    @Inject
    lateinit var viewModel: StatisticsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allStatisticsObserver()
    }

    private fun allStatisticsObserver() {
        viewModel.getStatisticsLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    handelAllStatisticsSuccess(result.data as StatisticsWrapper)
                }
                is Result.Loading -> {
                    handelAllStatisticsLoading(result)
                }
                is Result.Error -> {
                    handelAllStatisticsError(result)
                }
            }
        }
    }

    private fun handelAllStatisticsSuccess(data: StatisticsWrapper) {
        bind { this?.statistics = data }
    }

    private fun handelAllStatisticsLoading(loading: Result.Loading<*>) {
        if (loading.show) {
            binding?.errorView?.hide()
            binding?.progressBar.show()
            binding?.linearLayoutContentContainer.hide()
        } else {
            binding?.progressBar.hide()
            binding?.linearLayoutContentContainer.show()
        }
    }

    private fun handelAllStatisticsError(error: Result.Error<*>) {
        binding?.linearLayoutContentContainer.hide()
        binding?.errorView?.errorMessage(error.error.messageResource)
        binding?.errorView?.setOnRetryClickListener {
            viewModel.executeRequestStatistics()
        }
    }
}
