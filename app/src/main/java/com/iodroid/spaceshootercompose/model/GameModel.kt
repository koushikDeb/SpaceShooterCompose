package com.iodroid.spaceshootercompose.model

import kotlinx.coroutines.CoroutineScope

data class GameModel(
  val asteroidList: MutableList<AsteroidModel>,
  val laser: LaserModel,
  val ship: ShipModel,
  val animationScope: CoroutineScope
)
