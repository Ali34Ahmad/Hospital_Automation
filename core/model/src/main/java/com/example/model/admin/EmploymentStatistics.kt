package com.example.model.admin

import com.example.model.employee.EmployeeState

data class EmploymentStatistics(
    val employedCount: Int = 0,
    val stoppedCount: Int = 0,
    val resignedCount: Int = 0,
){
    fun getByState(state: EmployeeState)=
        when(state){
            EmployeeState.EMPLOYED -> employedCount
            EmployeeState.STOPPED -> stoppedCount
            EmployeeState.RESIGNED -> resignedCount
        }

}
