package com.example.weather

import com.google.gson.Gson
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var fm: FragmentManager
    private val briefWeatherFragment = BriefWeatherFragment()
    private val detailedWeatherFragment = DetailedWeatherFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        if (fm.findFragmentById(R.id.container_fragm) == null) {
            fm.beginTransaction().add(R.id.container_fragm, briefWeatherFragment).commit() }
        findViewById<Button>(R.id.btnBrief).setOnClickListener {
            loadWeather()
            fm.beginTransaction().replace(R.id.container_fragm, briefWeatherFragment).commit() }
        findViewById<Button>(R.id.btnDetailed).setOnClickListener {
            loadWeather()
            fm.beginTransaction().replace(R.id.container_fragm, detailedWeatherFragment).commit() }
        loadWeather()
    }
    private fun loadWeather() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val weatherData = fetchWeatherData()
                runOnUiThread {
                    updateFragments(weatherData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun fetchWeatherData(): WeatherResponse {
        val API_KEY = "4dac63af9f28277443bf2d581d6cc50b"
        val CITY = "Irkutsk"
        val weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$API_KEY&units=metric&lang=ru"
        val stream = URL(weatherURL).openStream()
        val reader = InputStreamReader(stream)
        return Gson().fromJson(reader, WeatherResponse::class.java)
    }
    private fun updateFragments(weatherData: WeatherResponse) {
        if (briefWeatherFragment.isAdded) briefWeatherFragment.updateWeather(weatherData)
        if (detailedWeatherFragment.isAdded) detailedWeatherFragment.updateWeather(weatherData)
    }
}
