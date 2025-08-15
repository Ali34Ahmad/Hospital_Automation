package com.example.employees_search.presentation

import com.example.model.employee.EmployeeState
import com.example.model.enums.ScreenState

interface EmployeesSearchUIAction{
    object Refresh: EmployeesSearchUIAction
    data class UpdateSearchQuery(val newValue: String): EmployeesSearchUIAction
    data class UpdateTab(val newTab: EmployeeState): EmployeesSearchUIAction
    data class UpdateScreenState(val newState: ScreenState): EmployeesSearchUIAction
}

interface EmployeesSearchNavigationActions{
    fun navigateToEmployeeProfile(employeeId: Int)
}