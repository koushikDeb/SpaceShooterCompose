package com.iodroid.spaceshootercompose.model

import androidx.compose.runtime.State

data class LaserModel(
  val laserState: State<Float>,
  var xPos:Float,
  var yPos:Float,
)