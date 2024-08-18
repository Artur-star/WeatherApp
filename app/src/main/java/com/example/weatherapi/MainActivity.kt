package com.example.weatherapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapi.databinding.ActivityMainBinding
import com.example.weatherapi.fragments.MainFragment
import org.json.JSONObject

const val API_KEY = "0aeb62cf9bfd4f3aacd175537241308&q"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
       supportFragmentManager
           .beginTransaction()
           .replace(R.id.placeHolder, MainFragment.newInstance())
           .commit()
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY=$name&aqi=no"
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val stringRequest: StringRequest = StringRequest(Request.Method.GET,
            url,
            { response ->
                val jsonObject = JSONObject(response)
                val currentData = jsonObject.getJSONObject("current").getString("temp_c")
                Log.d("MyLog", "Response: $currentData")
            },
            {
                Log.d("MyLog", "VolleyError: $it")
            })
        queue.add(stringRequest)
    }
}