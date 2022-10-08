package com.vickikbt.devtyme.data.mappers

import com.vickikbt.devtyme.data.network.models.*
import com.vickikbt.devtyme.domain.models.*

internal fun CategoryDto.toDomain(): Category {
    return Category(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        name = this.name,
        percent = this.percent,
        seconds = this.seconds,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun WeatherDto.toDomain(): Weather {
    return Weather(
        main = this.mainDto?.toDomain()
    )
}

internal fun MainDto.toDomain(): Main {
    return Main(
        temp = this.temp,
        feelsLike = this.feelsLike,
        pressure = this.pressure,
        humidity = this.humidity
    )
}

internal fun GrandTotalDto.toDomain(): GrandTotal {
    return GrandTotal(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun LanguageDto.toDomain(): Language {
    return Language(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        name = this.name,
        percent = this.percent,
        seconds = this.seconds,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun ProjectDto.toDomain(): Project {
    return Project(
        digital = this.digital,
        hours = this.hours,
        minutes = this.minutes,
        name = this.name,
        percent = this.percent,
        seconds = this.seconds,
        text = this.text,
        totalSeconds = this.totalSeconds
    )
}

internal fun RangeDto.toDomain(): Range {
    return Range(
        date = this.date,
        end = this.end,
        start = this.start,
        text = this.text,
        timezone = this.timezone
    )
}
