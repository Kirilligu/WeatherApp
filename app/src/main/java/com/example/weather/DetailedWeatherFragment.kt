package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
class DetailedWeatherFragment : Fragment() {
    private lateinit var tempText: TextView
    private lateinit var windText: TextView
    private lateinit var humidityText: TextView
    private lateinit var precipitationText: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detailed_weather, container, false).apply {
        tempText = findViewById(R.id.tempText)
        windText = findViewById(R.id.windText)
        humidityText = findViewById(R.id.humidityText)
        precipitationText = findViewById(R.id.precipitationText)
    }
    fun updateWeather(weatherData: WeatherResponse) {
        tempText.text = "Температура: ${weatherData.main.temp}°C"
        windText.text = "Ветер: ${weatherData.wind.speed} м/с, направление: ${weatherData.wind.deg}°"
        humidityText.text = "Влажность: ${weatherData.main.humidity}%"
        precipitationText.text = if (weatherData.weather[0].main == "Rain") "Осадки: дождь" else "Осадки: нет"
    }
}
