package com.example.model

data class VaccineWithDescription(
    val id:Int,
    val name:String,
    val description:String,
    val isAvailable:Boolean,
    val fromAge:Age,
    val toAge:Age,
)