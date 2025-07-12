package com.example.ext

import com.example.model.user.FullName

fun FullName.toAppropriateNameFormat(): String {
    val name = "${this.firstName} "
    val middleName = if (this.middleName!=null) "${this.middleName} " else ""
    val lastName = this.lastName
    return name+middleName+lastName
}
