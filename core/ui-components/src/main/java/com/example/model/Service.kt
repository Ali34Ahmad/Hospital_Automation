package com.example.model

import java.time.Duration

data class Service(
    val id:Int,
    val name:String,
    val description:String,
    val standardDuration:Duration
)
