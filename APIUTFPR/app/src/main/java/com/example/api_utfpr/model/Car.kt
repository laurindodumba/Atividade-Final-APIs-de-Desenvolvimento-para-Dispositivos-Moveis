package com.example.api_utfpr.model


data class Car(
    val id: String,
    val name: String,
    val year: String,
    val licence: String,
    val imageUrl: String,
    val place: Place
)

data class Place(
    val lat: Double,
    val long: Double
)