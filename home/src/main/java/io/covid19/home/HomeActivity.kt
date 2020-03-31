package io.covid19.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import io.covid19.core.activities.BaseActivity
import io.covid19.core.utils.hide
import io.covid19.core.utils.padding
import io.covid19.core.utils.show
import io.covid19.home.databinding.ActivityHomeBinding
import io.covid19.statistics.StatisticsViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModule: StatisticsViewModel

    @Inject
    lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    @Inject
    lateinit var adapterItems: MutableMap<Int, Fragment>

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        setSupportActionBar(binding?.toolbarMainActivity)
        initBottomNavigationListener()
        setupDefaultBottomNavigationSelection()
        initFloatActionButtonListener()
        initSearchEditText()
        initViewPager()
        viewModule.executeRequestStatistics()
    }

    @SuppressLint("WrongConstant")
    private fun initViewPager() {
        binding?.homeViewPager?.adapter = homeViewPagerAdapter
        binding?.homeViewPager?.offscreenPageLimit = VIEW_PAGER_OFFSCREEN
        binding?.homeViewPager?.isUserInputEnabled = false
    }

    private fun initFloatActionButtonListener() {
        binding?.floatActionButtonHomeActivityRefresh?.setOnClickListener {
            viewModule.executeRequestStatistics()
        }
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
            handelFragmentsView(it.title.toString())
            binding?.homeViewPager?.setCurrentItem(adapterItems.keys.indexOf(it.itemId), false)
            when (it.itemId) {
                R.id.home_bottom_navigation_countries -> {
                    handleCountriesFragmentView()
                }
                R.id.home_bottom_navigation_map -> {
                    binding?.toolbarMainActivity?.hide()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun handleCountriesFragmentView() {
        binding?.homeViewPager?.padding(bottom = 0)
        binding?.cardViewSearch.show()
        supportActionBar?.title = " "
    }

    private fun handelFragmentsView(toolbarTitle: String?) {
        binding?.homeViewPager?.padding(bottom = PADDING_BOTTOM)
        binding?.cardViewSearch.hide()
        binding?.toolbarMainActivity?.show()
        supportActionBar?.title = toolbarTitle
    }


    private fun initSearchEditText() {
        binding?.editTextCountriesSearch?.doOnTextChanged { text, _, _, _ ->
            viewModule.searchQuery(text.toString())
        }
    }

    private companion object {

        private const val VIEW_PAGER_OFFSCREEN = 4
        private const val PADDING_BOTTOM = 120
    }
}
