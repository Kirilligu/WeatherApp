package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class BriefWeatherFragment : Fragment() {
    private lateinit var tempText: TextView
    private lateinit var windSpeedText: TextView
    private lateinit var humidityText: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var windDirectionIcon: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brief_weather, container, false).apply {
            tempText = findViewById(R.id.tempText)
            windSpeedText = findViewById(R.id.windSpeedText)
            humidityText = findViewById(R.id.humidityText)
            weatherIcon = findViewById(R.id.weatherIcon)
            windDirectionIcon = findViewById(R.id.windDirectionIcon)
        } }
    fun updateWeather(weatherData: WeatherResponse) {
        tempText.text = "Температура: ${weatherData.main.temp}°C"
        windSpeedText.text = "Ветер: ${weatherData.wind.speed} м/с"
        humidityText.text = "Влажность: ${weatherData.main.humidity}%"
        weatherIcon.setImageResource(getWeatherIcon(weatherData.weather[0].icon))
        windDirectionIcon.rotation = weatherData.wind.deg.toFloat()
    }
    private fun getWeatherIcon(iconCode: String) = when (iconCode) {
        "01d", "01n" -> R.drawable.ic_sun
        "02d", "02n" -> R.drawable.ic_cloudy
        "03d", "03n", "04d", "04n" -> R.drawable.ic_cloud
        "09d", "09n", "10d", "10n" -> R.drawable.ic_rain
        "11d", "11n" -> R.drawable.ic_thunder
        "13d", "13n" -> R.drawable.ic_snow
        "50d", "50n" -> R.drawable.ic_fog
        else -> R.drawable.ic_unknown
    }
}
