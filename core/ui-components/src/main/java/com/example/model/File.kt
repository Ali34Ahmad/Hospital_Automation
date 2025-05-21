package com.example.model

data class File(
    val uploadingProgress:Int,
    val fileSizeWithBytes: Long,
    val fileName:String,
)