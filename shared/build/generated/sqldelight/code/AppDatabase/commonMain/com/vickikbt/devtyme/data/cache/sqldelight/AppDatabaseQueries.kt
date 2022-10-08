package com.vickikbt.devtyme.`data`.cache.sqldelight

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getToken(mapper: (
    accessToken: String?,
    refreshToken: String?,
    scope: String?,
    tokenType: String?,
    uid: String
  ) -> T): Query<T>

  public fun getToken(): Query<AccessTokenEntity>

  public fun <T : Any> getDailyGoal(mapper: (dailyGoal: Long?) -> T): Query<T>

  public fun getDailyGoal(): Query<DailyGoalEntity>

  public fun saveToken(AccessTokenEntity: AccessTokenEntity): Unit

  public fun deleteToken(): Unit

  public fun saveDailyGoal(dailyGoal: Long?): Unit
}
