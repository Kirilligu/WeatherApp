package com.example.weather

data class WeatherResponse(
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>)
data class Main(
    val temp: Float,
    val humidity: Int)
data class Wind(
    val speed: Float,
    val deg: Int)
data class Weather(
    val main: String,
    val description: String,
    val icon: String)
