package com.iodroid.spaceshootercompose.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.State

data class AsteroidModel(
  val asteroidObj: BaseAsteroid,
  var asteroidState: State<Float>,
  var explosionAnimatable: Animatable<Float, AnimationVector1D>,
  var explosionState: Animatable<Float, AnimationVector1D>,
  var isCollision: Boolean,
  var ongoingExplosion: Boolean,
  var isExploded: Boolean = false,
)