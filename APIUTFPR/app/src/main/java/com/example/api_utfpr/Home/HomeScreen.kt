package com.example.api_utfpr.Home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.api_utfpr.model.Car
import com.example.api_utfpr.network.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(onLogout: () -> Unit) {

    var cars by remember { mutableStateOf(listOf<Car>()) }

    LaunchedEffect(Unit) {
        RetrofitInstance.api.getCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                cars = response.body() ?: emptyList()
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                println("Erro API: ${t.message}")
            }
        })
    }

    Column {

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            onLogout()
        }) {
            Text("Logout")
        }

        LazyColumn {
            items(cars) { car ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text("Nome: ${car.name}")
                        Text("Ano: ${car.year}")
                        Text("Placa: ${car.licence}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Lat: ${car.place.lat}")
                        Text("Long: ${car.place.long}")

                        Spacer(modifier = Modifier.height(8.dp))

                        AsyncImage(
                            model = car.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    }
}