package com.example.model

import com.example.model.age.Age

data class VaccineWithDescription(
    val id:Int,
    val name:String,
    val description:String,
    val isAvailable:Boolean,
    val fromAge:Age,
    val toAge:Age,
)