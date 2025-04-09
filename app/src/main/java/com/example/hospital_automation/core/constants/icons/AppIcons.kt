package com.example.hospital_automation.core.constants.icons

import com.example.hospital_automation.R

sealed interface AppIcons {
    object Outlined:AppIcons{
        val chevronRight= R.drawable.ic_chevron_right
        val location= R.drawable.ic_location_outlined
        val father= R.drawable.ic_father
        val mother= R.drawable.ic_mother
    }
}