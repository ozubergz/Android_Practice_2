package com.example.android_practice_2.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_practice_2.model.Result
import com.example.android_practice_2.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _dailyWeather = MutableLiveData<Result>()

    val dailyWeather: LiveData<Result>
        get() = _dailyWeather

    fun fetchAPI(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = WeatherRepo.getForecast(city, "imperial", "d7de0d816f74102daad512fd9f3c13fc")
            if(response.isSuccessful) {
                val result = response.body()
                _dailyWeather.postValue(result)
            }
        }
    }
}