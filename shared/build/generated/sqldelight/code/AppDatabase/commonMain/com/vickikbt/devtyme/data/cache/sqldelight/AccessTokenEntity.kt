package com.vickikbt.devtyme.`data`.cache.sqldelight

import kotlin.String

public data class AccessTokenEntity(
  public val accessToken: String?,
  public val refreshToken: String?,
  public val scope: String?,
  public val tokenType: String?,
  public val uid: String
) {
  public override fun toString(): String = """
  |AccessTokenEntity [
  |  accessToken: $accessToken
  |  refreshToken: $refreshToken
  |  scope: $scope
  |  tokenType: $tokenType
  |  uid: $uid
  |]
  """.trimMargin()
}
