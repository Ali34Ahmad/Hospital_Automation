package com.example.data.mapper.enums

import com.example.model.enums.Role
import com.example.network.model.enums.RoleDto

fun RoleDto.toRole(): Role {
    return when(this){
        RoleDto.EMPLOYEE -> Role.EMPLOYEE
        RoleDto.DOCTOR -> Role.DOCTOR
        RoleDto.ADMIN -> Role.ADMIN
    }
}

fun Role.toRoleDto(): RoleDto {
    return when(this){
        Role.EMPLOYEE -> RoleDto.EMPLOYEE
        Role.DOCTOR -> RoleDto.DOCTOR
        Role.ADMIN -> RoleDto.ADMIN
    }
}