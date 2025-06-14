package com.example.model.doctor.appointment

data class AppointmentsStatisticsData(
    val upcoming: Int = 0,
    val passed: Int = 0,
    val missed: Int = 0,
){

    /**
     * get the corresponding statistic depending on the correct state.
     * @param state : the appointment state
     * @author Ali Mansoura
     */
    fun getByState(state: AppointmentState) =
        when(state){
            AppointmentState.UPCOMMING -> upcoming
            AppointmentState.PASSED -> passed
            AppointmentState.MISSED -> missed
        }
}
