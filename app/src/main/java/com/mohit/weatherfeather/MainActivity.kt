package com.mohit.weatherfeather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.mohit.weatherfeather.ui.screens.WeatherScreen
import com.mohit.weatherfeather.ui.screens.viewmodel.WeatherViewmodel
import com.mohit.weatherfeather.ui.theme.WeatherFeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewmodel = ViewModelProvider(this)[WeatherViewmodel::class.java]
        setContent {
            WeatherFeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(modifier = Modifier.padding(innerPadding)){
                       WeatherScreen(viewmodel)
                   }
                }
            }
        }
    }
}
