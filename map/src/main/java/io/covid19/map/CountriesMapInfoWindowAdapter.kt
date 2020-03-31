package io.covid19.map

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import io.covid19.core.utils.toObject
import io.covid19.data.models.Country
import io.covid19.map.databinding.ViewCountriesMapInfoWindowBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CountriesMapInfoWindowAdapter(private val context: Activity) : GoogleMap.InfoWindowAdapter {

    private var contentView: View? = null
    private var binding: ViewCountriesMapInfoWindowBinding? = null

    override fun getInfoContents(marker: Marker?): View? {
        init()
        handelWindowViewDimensions()
        bind(marker?.snippet?.toObject() as Country)
        return contentView
    }

    private fun init() {
        binding = ViewCountriesMapInfoWindowBinding.inflate(LayoutInflater.from(context))
        contentView = binding?.root
    }

    private fun bind(country: Country) {
        binding?.country = country
        binding?.executePendingBindings()
    }

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    private fun handelWindowViewDimensions() {
        val metrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metrics)
        contentView?.layoutParams =
            LinearLayout.LayoutParams(
                metrics.widthPixels - WINDOW_VIEW_HORIZONTAL_MARGIN,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
    }

    private companion object {

        private const val WINDOW_VIEW_HORIZONTAL_MARGIN = 100
    }
}