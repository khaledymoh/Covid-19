package io.covid19.map

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import io.covid19.core.fragments.BaseBindingFragment
import io.covid19.core.utils.observe
import io.covid19.core.utils.toBitmapDescriptor
import io.covid19.core.utils.toJson
import io.covid19.data.models.Country
import io.covid19.map.databinding.FragmentMapBinding
import io.covid19.statistics.StatisticsViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject


class MapFragment : BaseBindingFragment<FragmentMapBinding>(), OnMapReadyCallback {

    override val layoutId: Int = R.layout.fragment_map

    @Inject
    lateinit var viewModel: StatisticsViewModel

    @Inject
    lateinit var countriesMapInfoWindowAdapter: CountriesMapInfoWindowAdapter

    private var supportMapFragment: SupportMapFragment? = null
    private var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSupportMapFragment()
    }

    private fun initSupportMapFragment() {
        supportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container_view_map_fragment) as SupportMapFragment
        supportMapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        map?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(),
                R.raw.style_fragment_countries_map
            )
        )
        handelLocalCountriesObserver()
        initMapInfoWindow()
    }

    private fun handelLocalCountriesObserver() {
        viewModel.getLocalCountriesByRemoteLiveData().observe(viewLifecycleOwner) { result ->
            map?.clear()
            result.forEach {
                addMapMarker(it.value, viewModel.getCountry(it.key))
            }
        }
    }

    private fun addMapMarker(position: LatLng, country: Country) {
        map?.addMarker(
            MarkerOptions()
                .position(position)
                .title(country.countryName)
                .snippet(country.toJson())
                .icon(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_map_fragment_circle_marker
                    ).toBitmapDescriptor(viewModel.getTotalCasesByPercentage(country))
                )
        )
    }

    private fun initMapInfoWindow() {
        map?.setInfoWindowAdapter(countriesMapInfoWindowAdapter)
    }

    override fun onStart() {
        super.onStart()
        supportMapFragment?.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportMapFragment?.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        supportMapFragment?.onPause()
    }
}
