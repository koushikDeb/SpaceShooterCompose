package com.iodroid.spaceshootercompose

import android.content.res.Resources
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.imageResource
import com.iodroid.spaceshootercompose.common.getAsteroidResourceBasedOnAsteroidState
import com.iodroid.spaceshootercompose.common.getSpaceshipResourceBasedOnState
import com.iodroid.spaceshootercompose.model.AsteroidModel
import com.iodroid.spaceshootercompose.model.GameModel
import kotlinx.coroutines.launch

fun shipDragActions(
  gameState: GameModel,
  dragAmount: Offset
) {
  with(gameState) {
    animationScope.launch {
      ship.xPos += dragAmount.x
      ship.yPos += dragAmount.y

      launch {
        ship.animateX.animateTo(
          targetValue = ship.xPos,
          animationSpec = tween(durationMillis = 100)
        )
      }

      launch {
        ship.animateY.animateTo(
          targetValue = ship.yPos,
          animationSpec = tween(durationMillis = 100)
        )
      }
    }
  }

}

fun DrawScope.drawShip(
  res: Resources,
  gameState: GameModel
) {
  with(gameState)
  {
    drawImage(
      image = ImageBitmap.imageResource(
        res = res,
        id = getSpaceshipResourceBasedOnState(
          spaceShipState = ship.spaceShipState,
          ship.isMoving
        )
      ),
      Offset(
        x = ship.animateX.value,
        y = ship.animateY.value
      )
    )
  }
}

fun DrawScope.drawLaser(
  res: Resources,
  gameState: GameModel
) {
  with(gameState) {
    gameState.laser.xPos = ship.animateX.value + 100
    gameState.laser.yPos = ship.animateY.value + laser.laserState.value
    drawImage(
      image = ImageBitmap.imageResource(
        res = res,
        id = R.drawable.laser_green_12
      ),
      Offset(
        x = gameState.laser.xPos,
        y = ship.animateY.value + laser.laserState.value
      )
    )
  }
}

fun DrawScope.drawCollided(
  res: Resources,
  gameState: GameModel,
  asteroid: AsteroidModel,
) {
  startExplosionAnimation(gameState, asteroid)
  drawImage(
    image = ImageBitmap.imageResource(
      res = res,
      id = getAsteroidResourceBasedOnAsteroidState(asteroid.explosionState.value)
    ),
    Offset(
      x = asteroid.asteroidObj.xPos - 70,
      y = asteroid.asteroidObj.yPos - 100
    )
  )
}

private fun startExplosionAnimation(gameState: GameModel, asteroid: AsteroidModel) {
  gameState.animationScope.launch {
    asteroid.explosionState.animateTo(
      targetValue = 9f,
      keyframes<Float> {
        0f to 9f.at(1000).with(FastOutLinearInEasing)
      }
    )
  }
}

fun DrawScope.drawAsteroid(res: Resources, gameState: GameModel, asteroid: AsteroidModel) {

  with(asteroid) {
    isCollision = detectCollision(gameState)
    ongoingExplosion = (explosionState.value in 0.5f..8.5f)
    if (asteroid.isCollision || (asteroid.ongoingExplosion)) {
      drawCollided(res, gameState, asteroid)
      gameState.asteroidList.find {
          asteroidModel ->  asteroidModel.asteroidObj.id==asteroid.asteroidObj.id
      }?.isExploded = true
    }
    asteroidObj.yPos =  asteroidState.value
    drawImage(
      image = ImageBitmap.imageResource(
        res = res,
        id = R.drawable.astroid_tile000
      ),
      Offset(
        x = asteroidObj.xPos,
        y =  asteroidObj.yPos
    ),
      alpha = if(ongoingExplosion)0f else 1f
      ,
    )
  }
}

private fun AsteroidModel.detectCollision(gameState: GameModel) =
  (gameState.laser.xPos > asteroidObj.xPos && gameState.laser.xPos < asteroidObj.xPos + 70) &&
          (gameState.laser.yPos < asteroidObj.yPos + 70 && gameState.laser.yPos > asteroidObj.yPos)


