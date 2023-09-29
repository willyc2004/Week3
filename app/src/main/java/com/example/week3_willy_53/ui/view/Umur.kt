package com.example.week3_willy_53.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3_willy_53.ui.theme.Purple40
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Umur() {
    var name by rememberSaveable { mutableStateOf("") }
    var year by rememberSaveable { mutableStateOf("") }
    var showButton by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var age = 0

    var currentName = ""
    var currentYear = ""

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .background(Purple40)
                    ) {
                        Text(
                            text = "Age Calculator",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Filled.SentimentVerySatisfied,
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp),
                            tint = Color.Black
                        )


                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            label = { Text("Enter your name", color = Color.Gray) },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Purple40,
                                unfocusedBorderColor = Purple40,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            value = year,
                            onValueChange = {
                                year = it
                            },
                            label = { Text("Enter your birth year", color = Color.Gray) },
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Purple40,
                                unfocusedBorderColor = Purple40,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                        Button(
                            onClick = {
                                if (isValidYear(year)) {
                                    if (year.toInt() <= 2023) {
                                        age = 2023 - year.toInt()
                                        showButton = true
                                        currentName = name
                                        currentYear = year
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Hi, $name! Your Age is $age years")
                                        }
                                    } else {
                                        showButton = false
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Invalid input")
                                        }
                                    }
                                } else {
                                    showButton = false
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Invalid input")
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Purple40,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .width(220.dp)
                                .padding(vertical = 10.dp)
                                .height(50.dp),
                            enabled = name.isNotBlank() && year.isNotBlank()
                        )
                        {
                            Text(text = "CALCULATE YOUR AGE")
                        }

                        if (showButton) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Hi, $name! Your Age is $age years")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(horizontal = 10.dp),
                                border = BorderStroke(1.dp, Purple40),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Purple40
                                )
                            ) {
                                Text(
                                    text = "Hi, $name! Your Age is $age years",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }

                        if (currentName != name) {
                            showButton = false
                        }
                        if (currentYear != year) {
                            showButton = false
                        }
                    }
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        )
    }
}

fun isValidYear(input: String): Boolean {
    return input.matches(Regex("^(0|[1-9]\\d?\\d?\\d?|202[0-3])$"))
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UmurPreview() {
    Umur()
}