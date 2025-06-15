package com.example.model.helper

enum class AgeUnit{
    DAY,WEEK,MONTH,YEAR,NOT_SPECIFIED;
    companion object{
        fun getUnitFromString(string: String) =
            when{
                string.equals(DAY.name,ignoreCase = true)-> DAY
                string.equals(WEEK.name,ignoreCase = true)-> WEEK
                string.equals(MONTH.name,ignoreCase = true)-> MONTH
                string.equals(YEAR.name,ignoreCase = true)-> YEAR
                else-> NOT_SPECIFIED
            }
    }
}