package com.example.model.employee

enum class EmployeeState {
    EMPLOYED,
    STOPPED,
    RESIGNED;

    override fun toString(): String {
        return this.name.lowercase()
    }
}