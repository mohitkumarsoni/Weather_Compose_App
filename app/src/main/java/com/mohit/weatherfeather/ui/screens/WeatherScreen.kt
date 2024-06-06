package com.mohit.weatherfeather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.mohit.weatherfeather.api.response.NetworkResponse
import com.mohit.weatherfeather.ui.screens.viewmodel.WeatherViewmodel

@Composable
fun WeatherScreen(viewmodel: WeatherViewmodel) {
    var searchedCity by remember {mutableStateOf("")}
    val keyboardStatus = LocalSoftwareKeyboardController.current
    val weatherResponse = viewmodel.weatherResult.observeAsState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(modifier = Modifier.weight(1f) ,value = searchedCity, onValueChange = {searchedCity = it}, label = { Text(
                text = "Search location"
            )})
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = {
                keyboardStatus?.hide()
                viewmodel.getData(searchedCity)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search button")
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        when(val result = weatherResponse.value){
            is NetworkResponse.Error -> ErrorScreen(result.message)
            NetworkResponse.Loading -> LoadingScreen()
            is NetworkResponse.Success -> SuccessScreen(result.data)
            null -> {}
        }
    }
}
























