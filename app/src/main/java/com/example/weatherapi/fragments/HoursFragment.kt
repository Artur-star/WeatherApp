package com.example.weatherapi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapi.MainViewModel
import com.example.weatherapi.adapters.WeatherAdapter
import com.example.weatherapi.adapters.WeatherModel
import com.example.weatherapi.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()
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
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursList(it))
        }
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter
       
    }
    
    private fun getHoursList(wItem: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(wItem.hours)
        val list = ArrayList<WeatherModel>()
        for(i in 0 until hoursArray.length()) {
            val item = WeatherModel(
                city = "",
                time = (hoursArray[i] as JSONObject).getString("time"),
                condition = (hoursArray[i] as JSONObject).getJSONObject("condition")
                    .getString("text"),
                currentTemp = (hoursArray[i] as JSONObject).getString("temp_c"),
                maxTemp = "", minTemp = "",
                imageUrl = (hoursArray[i] as JSONObject).getJSONObject("condition")
                    .getString("icon"),
                hours = ""
            )
            list.add(item)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}