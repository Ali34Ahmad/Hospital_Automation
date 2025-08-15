package com.example.model.admin

data class DepartmentStatistics(
    val activeCount: Int = 0,
    val stoppedCount: Int = 0,
    val previousCount: Int = 0,
){
    fun getByState(state: DepartmentState)=
        when(state){
            DepartmentState.ACTIVE -> activeCount
            DepartmentState.STOPPED -> stoppedCount
            DepartmentState.PREVIOUS -> previousCount
        }

}

