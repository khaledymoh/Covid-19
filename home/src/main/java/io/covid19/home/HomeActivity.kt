package io.covid19.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.covid19.analytics.AnalyticsFragment
import io.covid19.core.activities.BaseActivity
import io.covid19.core.utils.changeFragment
import io.covid19.countries.CountriesFragment
import io.covid19.home.databinding.ActivityHomeBinding
import io.covid19.map.MapFragment
import io.covid19.overview.OverviewFragment
import io.covid19.statistics.StatisticsViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModule: StatisticsViewModel

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        setSupportActionBar(binding?.toolbarMainActivity)
        initBottomNavigationListener()
        setupDefaultBottomNavigationSelection()
        viewModule.executeRequestStatistics()
    }

    private fun initContentView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
    }

    private fun setupDefaultBottomNavigationSelection() {
        binding?.bottomNavigationHomeActivity?.selectedItemId = R.id.home_bottom_navigation_overview
    }

    private fun initBottomNavigationListener() {
        binding?.bottomNavigationHomeActivity?.setOnNavigationItemSelectedListener {
            supportActionBar?.title = it.title
            when (it.itemId) {
                R.id.home_bottom_navigation_overview -> {
                    navigateToFragment(OverviewFragment(), OverviewFragment::class.java.simpleName)
                }
                R.id.home_bottom_navigation_countries -> {
                    navigateToFragment(CountriesFragment(), CountriesFragment::class.java.simpleName)
                }
                R.id.home_bottom_navigation_analytics -> {
                    navigateToFragment(AnalyticsFragment(), AnalyticsFragment::class.java.simpleName)
                }
                R.id.home_bottom_navigation_map -> {
                    navigateToFragment(MapFragment(), MapFragment::class.java.simpleName)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun navigateToFragment(fragment: Fragment, tag: String) {
        changeFragment(fragment, R.id.frame_layout_main_navigation_container, tag)
    }
}
