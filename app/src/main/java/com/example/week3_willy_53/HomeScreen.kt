package com.example.week3_willy_53

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate("triangle_area")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
            content = {
                Text(
                    text = "Triangle Area",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                )
            }
        )

        Button(
            onClick = {
                navController.navigate("bmi")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
            content = {
                Text(
                    text = "BMI",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                )
            }
        )

        Button(
            onClick = {
                navController.navigate("umur")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
            content = {
                Text(
                    text = "Age Calculator",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                )
            }
        )
        Button(
            onClick = {
                navController.navigate("score")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
            content = {
                Text(
                    text = "Student Score",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                )
            }
        )

    }
}
