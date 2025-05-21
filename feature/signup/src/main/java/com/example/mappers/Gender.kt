package com.example.mappers

import com.example.constants.enums.Gender

fun Gender.toNetworkGender():com.example.network.constants.Gender{
    return com.example.network.constants.Gender.entries
        .find { it.name==name }?:com.example.network.constants.Gender.MALE
}

fun com.example.network.constants.Gender.toUiGender():Gender{
    return Gender.entries
        .find { it.name==name }?:Gender.MALE
}

