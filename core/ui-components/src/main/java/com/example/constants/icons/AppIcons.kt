package com.example.constants.icons

import com.example.ui_components.R

sealed interface AppIcons {
    data object Outlined: AppIcons {
        val add = R.drawable.add
        val man = R.drawable.ic_male
        val attachment = R.drawable.attachment
        val call = R.drawable.call
        val mail = R.drawable.mail
        val calender = R.drawable.calendar
        val SpecificDate = R.drawable.ic_event_date
        val location = R.drawable.location_on
        val father = R.drawable.face
        val mother = R.drawable.face_4
        val child = R.drawable.child_care
        val certificate = R.drawable.ic_certificate
        val deactivateAccount = R.drawable.person_off
        val send = R.drawable.send
        val accountCircle = R.drawable.account_circle
        val close = R.drawable.close
        val menu = R.drawable.menu
        val arrowBack = R.drawable.arrow_back
        val chevronRight = R.drawable.chevron_right
        val female = R.drawable.ic_female
        val male = R.drawable.ic_male
        val notification = R.drawable.notifications
        val search2 = R.drawable.search2
        val file = R.drawable.ic_file
        val check = R.drawable.ic_check
        val email = R.drawable.ic_email
        val employmentHistory = R.drawable.ic_employement_history
    }
}