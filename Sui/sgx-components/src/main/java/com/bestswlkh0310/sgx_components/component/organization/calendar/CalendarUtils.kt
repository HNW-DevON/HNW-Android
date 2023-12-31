package com.bestswlkh0310.sgx_components.component.organization.calendar

import com.bestswlkh0310.sgx_components.component.organization.calendar.schedule.Schedule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDate.getLocalDateTime(): LocalDateTime =
    LocalDateTime.of(this, LocalTime.of(9, 0))

fun getLocalDateTime(year: Int, month: Int, day: Int): LocalDateTime =
    LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(9, 0))

fun LocalDate.getMonthDays(): List<MonthDay> {

    val monthDays = mutableListOf<MonthDay>()
    val year = this.year
    val month = this.monthValue

    val date = LocalDate.of(year, month, 1)
    // 0 ~ 6으로 일요일이 0, 토요일이 6
    val dayOfWeek = date.dayOfWeek.value
    val dayOfWeekData = if (dayOfWeek == 7) 0 else dayOfWeek

    // 시작일 앞의 빈 주 채우기
    for (i in 0 until dayOfWeekData) {
        monthDays.add(MonthDay(-1, -1))
    }

    // 실제 값 채우기
    for (i in 1..this.lengthOfMonth()) {
        val realDate = LocalDate.of(year, month, i)
        val realDayOfWeekData = realDate.dayOfWeek.value
        monthDays.add(MonthDay(i, if (realDayOfWeekData == 7) 0 else realDayOfWeekData))
    }

    return monthDays
}

data class MonthDay(
    val day: Int,
    val dayOfWeek: Int, // 0 ~ 6
)

enum class DayScheduleType {
    START, MIDDLE, END
}

data class DaySchedule(
    val type: DayScheduleType,
    val schedule: Schedule,
)

fun LocalDate.hasSchedules(schedules: List<Schedule>): List<DaySchedule> {
    val daySchedules = mutableListOf<DaySchedule>()
    schedules.forEach { schedule ->
        if (this.isAfter(schedule.startDateTime.toLocalDate()) && this.isBefore(schedule.endDateTime.toLocalDate())) {
            daySchedules.add(DaySchedule(DayScheduleType.MIDDLE, schedule))
        } else if (this.isEqual(schedule.startDateTime.toLocalDate())) {
            daySchedules.add(DaySchedule(DayScheduleType.START, schedule))
        } else if (this.isEqual(schedule.endDateTime.toLocalDate())) {
            daySchedules.add(DaySchedule(DayScheduleType.END, schedule))
        }
    }
    val comparator: Comparator<DaySchedule> = Comparator { schedule1, schedule2 ->
        if (
            (schedule1.schedule.startDateTime < schedule2.schedule.startDateTime) &&
            (schedule1.schedule.endDateTime > schedule2.schedule.endDateTime)
        ) {
            -1
        } else if (
            (schedule1.schedule.startDateTime > schedule2.schedule.startDateTime) &&
            (schedule1.schedule.endDateTime < schedule2.schedule.endDateTime)
        ) {
            1
        } else {
            0
        }
    }
    daySchedules.sortWith(comparator)
    return daySchedules
}

fun LocalDateTime.toKoreanDateTime(): String {
    val isAfter = if (hour >= 12) {
        "오후"
    } else {
        "오전"
    }
    return String.format("${monthValue}월 ${dayOfMonth}일 $isAfter %02d:%02d", hour, minute)
}
