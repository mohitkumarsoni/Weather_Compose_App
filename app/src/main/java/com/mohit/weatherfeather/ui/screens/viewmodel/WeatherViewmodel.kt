package com.mohit.weatherfeather.ui.screens.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohit.weatherfeather.api.Constants
import com.mohit.weatherfeather.api.RetrofitInstance
import com.mohit.weatherfeather.api.response.NetworkResponse
import com.mohit.weatherfeather.ui.models.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewmodel : ViewModel() {

    private var _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(cityName:String) = viewModelScope.launch{
        _weatherResult.value = NetworkResponse.Loading
        try {
            val responseBody = RetrofitInstance.apiAccess.getWeather(apiKey = Constants.API_KEY, cityName = cityName)
            if (responseBody.isSuccessful) {
                responseBody.body()?.let {
                    _weatherResult.value = NetworkResponse.Success(it)
                }
            }else{
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
        }catch (e:Exception){
            _weatherResult.value = NetworkResponse.Error("Failed to load data")
            e.localizedMessage?.let { Log.d("Failed to load data", it) }
        }
    }

}