package com.example.weatherapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapi.adapters.WeatherAdapter
import com.example.weatherapi.adapters.WeatherModel
import com.example.weatherapi.databinding.FragmentHoursBinding

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(
            WeatherModel(city = "", time = "12:00",
                condition = "Sunny", currentTemp = "28°C",
                minTemp = "", maxTemp = "",
                imageUrl = "", hours = ""),
            WeatherModel(city = "", time = "13:00",
                condition = "Sunny", currentTemp = "30°C",
                minTemp = "", maxTemp = "",
                imageUrl = "", hours = ""),
            WeatherModel(city = "", time = "14:00",
                condition = "Rainy", currentTemp = "22°C",
                minTemp = "", maxTemp = "",
                imageUrl = "", hours = "")
        )
        adapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}