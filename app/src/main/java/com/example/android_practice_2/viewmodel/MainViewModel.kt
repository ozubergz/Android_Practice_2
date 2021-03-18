package com.example.android_practice_2.viewmodel


import android.util.Log
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
            val response = WeatherRepo.getForecast(city, "imperial", "")
            val result = response.body()
            Log.d("MainViewModel", "fetchAPI: $result")
            _dailyWeather.postValue(result)
        }
    }
}