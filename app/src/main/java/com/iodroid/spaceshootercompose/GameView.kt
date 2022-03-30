package com.iodroid.spaceshootercompose.ui.theme

import android.content.res.Resources
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import com.iodroid.spaceshootercompose.*
import com.iodroid.spaceshootercompose.common.createRandomList
import com.iodroid.spaceshootercompose.model.GameModel


@Composable
fun GameView(res: Resources, maxWidth: Dp) {

  val gameState = GameState(asteroidsBaseList = createRandomList(maxWidth = maxWidth))
  createGameCanvas(gameState = gameState) { drawScope ->
    drawScope.drawShip(res, gameState)
    drawScope.drawLaser(res, gameState)
    destroyAsteroids(gameState)
    for (asteroid in gameState.asteroidList) {
      drawScope.drawAsteroid(res, gameState, asteroid)
    }
  }
}


private fun destroyAsteroids(gameState: GameModel) {
  gameState.asteroidList.removeAll { asteroid ->
    asteroid.isExploded && (asteroid.explosionState.value > 8.5)
  }
}

@Composable
private fun createGameCanvas(
  gameState: GameModel,
  draw: (DrawScope) -> Unit
) {
  Canvas(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
      .pointerInput(Unit) {
        detectDragGestures(
          onDragStart = {
            gameState.ship.isMoving = true
          },
          onDragEnd = {
            gameState.ship.isMoving = false
          }
        ) { change, dragAmount ->
          change.consumeAllChanges()
          shipDragActions(gameState, dragAmount)
        }
      },
  ) {
    draw(this)
  }
}


