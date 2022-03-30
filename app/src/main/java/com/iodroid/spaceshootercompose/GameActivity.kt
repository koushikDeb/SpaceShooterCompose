package com.iodroid.spaceshootercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.iodroid.spaceshootercompose.model.BaseAsteroid
import com.iodroid.spaceshootercompose.ui.theme.GameView
import com.iodroid.spaceshootercompose.ui.theme.SpaceShooterComposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SpaceShooterComposeTheme {
        BoxWithConstraints(
          modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
        ) {
          GameView(applicationContext.resources,maxWidth)
        }
      }
    }
  }
}