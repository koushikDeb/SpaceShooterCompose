package com.iodroid.spaceshootercompose.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.State

data class ShipModel(
  var xPos:Float,
  var yPos:Float,
  var isMoving:Boolean = false,
  val animateX: Animatable<Float, AnimationVector1D>,
  val animateY: Animatable<Float, AnimationVector1D>,
  val spaceShipState: State<Float>,
  )
