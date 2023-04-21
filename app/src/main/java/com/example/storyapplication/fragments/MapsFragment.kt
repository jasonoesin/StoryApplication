package com.example.storyapplication.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapplication.R
import com.example.storyapplication.responses.GetResponse
import com.example.storyapplication.retrofit.ApiConfig
import com.example.storyapplication.utilities.LogUtil

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true

        getStories(googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val boundsBuilder = LatLngBounds.Builder()

    private fun getStories(googleMap: GoogleMap) {
        val client = ApiConfig.getRetrofitApiHeader().getStoriesWithLocation()

        client.enqueue(object : Callback<GetResponse> {
            override fun onResponse(
                call: Call<GetResponse>,
                response: Response<GetResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    responseBody.listStory.forEach {
                        val latLng = LatLng(it.lat, it.lon)
                        googleMap.addMarker(MarkerOptions().position(latLng).title(it.name))
                        boundsBuilder.include(latLng)
                    }

                    val bounds: LatLngBounds = boundsBuilder.build()
                    googleMap.animateCamera(
                        CameraUpdateFactory.newLatLngBounds(
                            bounds,
                            resources.displayMetrics.widthPixels,
                            resources.displayMetrics.heightPixels,
                            300
                        )
                    )
                } else {
                    LogUtil.LogD("onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {

            }
        })
    }


}