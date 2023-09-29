package com.example.week3_willy_53

import HomeScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week3_willy_53.ui.theme.Week3_Willy_53Theme
import com.example.week3_willy_53.ui.view.BMI
import com.example.week3_willy_53.ui.view.Score
import com.example.week3_willy_53.ui.view.TriangleArea
import com.example.week3_willy_53.ui.view.Umur

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week3_Willy_53Theme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(navController = navController)
                        }
                        composable("bmi") {
                            BMI()
                        }
                        composable("triangle_area") {
                            TriangleArea()
                        }
                        composable("umur") {
                            Umur()
                        }
                        composable("score") {
                            Score()
                        }
                    }
                }
            }
        }
    }
}
