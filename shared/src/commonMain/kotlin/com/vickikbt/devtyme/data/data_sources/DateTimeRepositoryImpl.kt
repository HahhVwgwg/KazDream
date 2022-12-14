package com.vickikbt.devtyme.data.data_sources

import com.vickikbt.devtyme.domain.repositories.DateTimeRepository
import com.vickikbt.devtyme.domain.utils.daysShift
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeRepositoryImpl : DateTimeRepository {

    override fun getTimeOfDay(): Flow<String> {
        return when (
            Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).hour
        ) {
            in 0..11 -> flowOf("Доброе утро, ")
            in 12..15 -> flowOf("Добрый день,")
            else -> flowOf("Добрый вечер, ")
        }
    }

    override fun getCurrentDate(): Flow<String> {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        println(currentDate.toString())
        return flowOf(currentDate.toString())
    }

    override fun getDaysOfWeek(): Flow<List<String>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val days = mutableListOf<String>()
        for (i in 0 until DayOfWeek.values().count()) {
            days.add(today.daysShift(i).toString())
        }
        return flowOf(days)
    }
}
