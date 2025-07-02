package com.example.model.doctor.appointment
enum class AppointmentState{
    UPCOMMING,
    PASSED,
    MISSED,
    PENDING,
    ;

    override fun toString(): String {
        return name.lowercase().replaceFirstChar { it.uppercase() }
    }
    companion object{
        fun getFromString(string: String) =
            when{
                string.contains(UPCOMMING.name, ignoreCase = true) -> UPCOMMING
                string.contains(PASSED.name, ignoreCase = true) -> PASSED
                else-> MISSED
            }
    }
}
