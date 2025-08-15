package com.example.model.admin

enum class DepartmentState {
    ACTIVE,
    STOPPED,
    PREVIOUS;

    override fun toString(): String {
        return this.name.lowercase()
    }
}