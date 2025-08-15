package com.example.employees_search.presentation.preview

import androidx.paging.PagingData
import com.example.employees_search.presentation.EmployeesSearchNavigationActions
import com.example.model.employee.EmployeeData
import kotlinx.coroutines.flow.flowOf

private val employeesList = listOf(
    EmployeeData(
        id = 1,
        firstName = "Zen",
        middleName = "Bassam",
        lastName = "Mansoura",
        imageUrl = ""
    ),
    EmployeeData(
        id = 1,
        firstName = "Ali",
        middleName = "Bassam",
        lastName = "Mansoura",
        imageUrl = ""
    ),
    EmployeeData(
        id = 1,
        firstName = "Yousef",
        middleName = "Mahmod",
        lastName = "Shmeless",
        imageUrl = ""
    ),
)

val employeesFlow = flowOf(
    PagingData.from(employeesList)
)

val mockNavigationActions = object : EmployeesSearchNavigationActions{
    override fun navigateToEmployeeProfile(employeeId: Int) {

    }
}