package com.iodroid.spaceshootercompose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.iodroid.spaceshootercompose.common.InfiniteRepeatableSpecAnimation
import com.iodroid.spaceshootercompose.common.createRandomList
import com.iodroid.spaceshootercompose.model.*
import kotlin.random.Random


@Composable
fun GameState(asteroidsBaseList: MutableList<BaseAsteroid>): GameModel {

  //Create Laser
  var laserX by remember { mutableStateOf(0f) }
  var laserY by remember { mutableStateOf(0f) }
  val infiniteLaserTransition = rememberInfiniteTransition()
  val laserState = infiniteLaserTransition.animateFloat(
    initialValue = 0f,
    targetValue = -2000f,
    animationSpec = InfiniteRepeatableSpecAnimation(500)
  )
  //Laser//

  //Create Asteroids
  val asteroidList = CreateAsteroidsListFrom(asteroidsBaseList)
  //Asteroids//


  //Create Ship
  var xPos by remember { mutableStateOf(250f) }
  var yPos by remember { mutableStateOf(1500f) }
  val animationScope = rememberCoroutineScope()
  var animatableX = remember { Animatable(initialValue = xPos) }
  var animatableY = remember { Animatable(initialValue = yPos) }
  var isMoving by remember {
    mutableStateOf(false)
  }
  val infiniteTransition = rememberInfiniteTransition()
  val spaceShipState = infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 5f,
    animationSpec = InfiniteRepeatableSpecAnimation(500)
  )

  val ship = ShipModel(
    xPos = xPos,
    yPos = yPos,
    isMoving = isMoving,
    animateX = animatableX,
    animateY = animatableY,
    spaceShipState = spaceShipState
  )
  //Ship//

  val laserModel = LaserModel(laserState, laserX, laserY)
  return GameModel(asteroidList, laserModel, ship, animationScope)
}

@Composable
fun CreateAsteroidsListFrom(asteroidsBaseList: MutableList<BaseAsteroid>): MutableList<AsteroidModel> {
  val asteroidList = mutableListOf<AsteroidModel>()
  for (baseAsteroid: BaseAsteroid in asteroidsBaseList) {

    val explosion by remember { mutableStateOf(0f) }

    val explosionState = remember { Animatable(initialValue = 0f) }

    val explosionAnimatable = remember {
      Animatable(initialValue = explosion)
    }
    val isCollision by remember {
      mutableStateOf(false)
    }

    val ongoingExplosion: Boolean by remember {
      mutableStateOf(false)
    }

    val infiniteTransition = rememberInfiniteTransition()
    val asteroidState = infiniteTransition.animateFloat(
      initialValue = 0f,
      targetValue = 3000f,
      animationSpec = InfiniteRepeatableSpecAnimation(Random.nextInt(4, 10) * 1000)
    )


    asteroidList.add(
      AsteroidModel(
        asteroidObj = baseAsteroid,
        asteroidState = asteroidState,
        explosionAnimatable = explosionAnimatable,
        explosionState = explosionState,
        isCollision = isCollision,
        ongoingExplosion = ongoingExplosion
      )
    )
  }
  return asteroidList
}
