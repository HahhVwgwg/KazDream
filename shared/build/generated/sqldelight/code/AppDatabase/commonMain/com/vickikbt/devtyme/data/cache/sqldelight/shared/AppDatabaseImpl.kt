package com.vickikbt.devtyme.`data`.cache.sqldelight.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.devtyme.`data`.cache.sqldelight.AccessTokenEntity
import com.vickikbt.devtyme.`data`.cache.sqldelight.AppDatabase
import com.vickikbt.devtyme.`data`.cache.sqldelight.AppDatabaseQueries
import com.vickikbt.devtyme.`data`.cache.sqldelight.DailyGoalEntity
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE AccessTokenEntity  (
          |accessToken TEXT DEFAULT NULL,
          |refreshToken TEXT DEFAULT NULL,
          |scope TEXT DEFAULT NULL,
          |tokenType TEXT DEFAULT NULL,
          |uid TEXT DEFAULT NULL PRIMARY KEY
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE DailyGoalEntity(
          |dailyGoal INTEGER DEFAULT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val getToken: MutableList<Query<*>> = copyOnWriteList()

  internal val getDailyGoal: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getToken(mapper: (
    accessToken: String?,
    refreshToken: String?,
    scope: String?,
    tokenType: String?,
    uid: String
  ) -> T): Query<T> = Query(622978332, getToken, driver, "AppDatabase.sq", "getToken",
      "SELECT * FROM AccessTokenEntity") { cursor ->
    mapper(
      cursor.getString(0),
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4)!!
    )
  }

  public override fun getToken(): Query<AccessTokenEntity> = getToken { accessToken, refreshToken,
      scope, tokenType, uid ->
    AccessTokenEntity(
      accessToken,
      refreshToken,
      scope,
      tokenType,
      uid
    )
  }

  public override fun <T : Any> getDailyGoal(mapper: (dailyGoal: Long?) -> T): Query<T> =
      Query(-109248913, getDailyGoal, driver, "AppDatabase.sq", "getDailyGoal",
      "SELECT * FROM DailyGoalEntity") { cursor ->
    mapper(
      cursor.getLong(0)
    )
  }

  public override fun getDailyGoal(): Query<DailyGoalEntity> = getDailyGoal { dailyGoal ->
    DailyGoalEntity(
      dailyGoal
    )
  }

  public override fun saveToken(AccessTokenEntity: AccessTokenEntity): Unit {
    driver.execute(1478460611, """
    |INSERT OR REPLACE INTO AccessTokenEntity(accessToken,refreshToken,scope,tokenType,uid)
    |VALUES (?, ?, ?, ?, ?)
    """.trimMargin(), 5) {
      bindString(1, AccessTokenEntity.accessToken)
      bindString(2, AccessTokenEntity.refreshToken)
      bindString(3, AccessTokenEntity.scope)
      bindString(4, AccessTokenEntity.tokenType)
      bindString(5, AccessTokenEntity.uid)
    }
    notifyQueries(1478460611, {database.appDatabaseQueries.getToken})
  }

  public override fun deleteToken(): Unit {
    driver.execute(-1512327275, """DELETE FROM AccessTokenEntity""", 0)
    notifyQueries(-1512327275, {database.appDatabaseQueries.getToken})
  }

  public override fun saveDailyGoal(dailyGoal: Long?): Unit {
    driver.execute(801403542, """
    |INSERT OR REPLACE INTO DailyGoalEntity(dailyGoal)
    |VALUES (?)
    """.trimMargin(), 1) {
      bindLong(1, dailyGoal)
    }
    notifyQueries(801403542, {database.appDatabaseQueries.getDailyGoal})
  }
}
