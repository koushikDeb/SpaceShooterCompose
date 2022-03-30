package com.iodroid.spaceshootercompose.common

import androidx.compose.ui.unit.Dp
import com.iodroid.spaceshootercompose.model.BaseAsteroid
import kotlin.random.Random

fun createRandomList(maxWidth: Dp): MutableList<BaseAsteroid> {
  var asteroidsList = mutableListOf<BaseAsteroid>()
  val asteroidsNo = Random.nextInt(3, 10)
  for (i in 0..asteroidsNo) {
    asteroidsList.add(
      BaseAsteroid(
        i.toLong(),
        (Random.nextFloat() * (maxWidth.value * 2.5f)),
        100f
      )
    )
  }
  return asteroidsList
}