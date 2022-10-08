package com.vickikbt.devtyme.`data`.cache.sqldelight

import kotlin.Long
import kotlin.String

public data class DailyGoalEntity(
  public val dailyGoal: Long?
) {
  public override fun toString(): String = """
  |DailyGoalEntity [
  |  dailyGoal: $dailyGoal
  |]
  """.trimMargin()
}
