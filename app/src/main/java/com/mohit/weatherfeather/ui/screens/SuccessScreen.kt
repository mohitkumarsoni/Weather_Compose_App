package com.mohit.weatherfeather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.mohit.weatherfeather.ui.models.WeatherModel

@Composable
fun SuccessScreen(data: WeatherModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom
        ) {

            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "location icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location?.name.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = data.location?.country.toString(), fontSize = 16.sp, fontWeight = FontWeight.Light)

        }

        HeightSpace(height = 26)

        Text(text = data.current?.temp_c+" °c", fontSize = 36.sp)
        
        HeightSpace(height = 12)

        SubcomposeAsyncImage(modifier = Modifier.size(128.dp) ,model = "https:${data.current?.condition?.icon}", contentDescription = "icon")

        HeightSpace(height = 36)

        Card(modifier = Modifier.fillMaxWidth()) {
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(18.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column {
                    CardKeyVal(key = "Local Date", value = data.location?.localtime?.split(" ")?.get(0)?: "")
                    HeightSpace(height = 8)
                    CardKeyVal(key = "Local Time", value = data.location?.localtime?.split(" ")?.get(1)?: "")

                }

                Column {

                    CardKeyVal(key = "Current Temp", value = data.current?.temp_c+" °c" )
                    HeightSpace(height = 8)
                    CardKeyVal(key = "Feels Like", value = data.current?.feelslike_c+" °c" )

                }

                Column {
                    CardKeyVal(key = "Wind Km/h", value = data.current?.wind_kph.toString() )
                    HeightSpace(height = 8)
                    CardKeyVal(key = "Wind Chill", value = data.current?.windchill_c+" °c" )
                }

            }
        }
        
        
        
    }
}

@Composable
fun CardKeyVal(key:String, value:String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = value, fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Text(text = key, fontSize = 13.sp, fontWeight = FontWeight.Thin)
        HeightSpace(height = 18)
    }
}

@Composable
fun HeightSpace(height:Int) {
    Spacer(modifier = Modifier.height(height.dp))
}