package com.example.model.doctor.appointment

enum class SortType{
    ASC,
    DESC;

    override fun toString(): String {
        return super.toString().uppercase()
    }
}