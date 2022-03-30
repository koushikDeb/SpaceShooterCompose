package com.iodroid.spaceshootercompose.common

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.geometry.Offset
import com.iodroid.spaceshootercompose.R
import com.iodroid.spaceshootercompose.model.AsteroidModel

@Composable
fun InfiniteRepeatableSpecAnimation(duration: Int): InfiniteRepeatableSpec<Float> =
  infiniteRepeatable(
    animation = tween(
      durationMillis = duration,
      easing = LinearEasing
    )
  )

@Composable
fun OffsetAnimation(duration: Int,offsetX: Float,offsetY: Float): State<Offset> {
  return animateOffsetAsState(
    Offset(offsetX,offsetY),
   tween(
      durationMillis = duration,
      easing = LinearEasing
    )
  )
}

fun getSpaceshipResourceBasedOnState(spaceShipState: State<Float>,isMoving:Boolean): Int {
  if(!isMoving){
    return (R.drawable.ship_tile004)
  }
  when (spaceShipState.value) {
    in 0.0f..1.0f -> {
      return (R.drawable.ship_tile004)
    }
    in 1.0f..2.0f -> {
      return (R.drawable.ship_tile005)
    }
    in 2.0f..3.0f -> {
      return (R.drawable.ship_tile006)
    }
    in 3.0f..4.0f -> {
      return (R.drawable.ship_tile007)
    }
  }
  return (R.drawable.ship_tile004)
}


fun getAsteroidResourceBasedOnAsteroidState(explosionState: Float): Int {
  when (explosionState) {
    in 0.0f..1.0f -> {
      return (R.drawable.explosion_tile000)
    }
    in 1.0f..2.0f -> {
      return (R.drawable.explosion_tile001)
    }
    in 2.0f..3.0f -> {
      return (R.drawable.explosion_tile002)
    }
    in 3.0f..4.0f -> {
      return (R.drawable.explosion_tile003)
    }
    in 4.0f..5.0f -> {
      return (R.drawable.explosion_tile004)
    }
    in 5.0f..6.0f -> {
      return (R.drawable.explosion_tile005)
    }
    in 6.0f..7.0f -> {
      return (R.drawable.explosion_tile006)
    }
    in 7.0f..8.0f -> {
      return (R.drawable.explosion_tile007)
    }
    in 8.0f..9.0f -> {
      return (R.drawable.explosion_tile008)
    }
    in 9.0f..10.0f -> {
      return (R.drawable.explosion_tile008)
    }
  }
  return (R.drawable.explosion_tile008)
}
