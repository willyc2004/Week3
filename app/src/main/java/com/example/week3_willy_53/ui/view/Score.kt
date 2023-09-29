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
import androidx.compose.material.icons.filled.School
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
fun Score() {
    var score1 by rememberSaveable { mutableStateOf("") }
    var score2 by rememberSaveable { mutableStateOf("") }
    var score3 by rememberSaveable { mutableStateOf("") }
    var showButton by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var average = 0.0

    var currScore1 = ""
    var currScore2 = ""
    var currScore3 = ""

    var scoreText = ""


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
                            text = "Student Score",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(16.dp) // Add padding to the Text
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
                            Icons.Filled.School,
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp),
                            tint = Color.Black
                        )


                        OutlinedTextField(
                            value = score1,
                            onValueChange = {
                                score1 = it
                            },
                            label = { Text("Joren's Score", color = Color.Gray) },
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
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            value = score2,
                            onValueChange = {
                                score2 = it
                            },
                            label = { Text("Willy's Score", color = Color.Gray) },
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
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            value = score3,
                            onValueChange = { score3 = it
                            },
                            label = { Text("Yobel's Score", color = Color.Gray) },
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
                                if(isValidScore(score1) && isValidScore(score2) && isValidScore(score3)) {
                                    average = ((score1.toDouble() + score2.toDouble() + score3.toDouble()) / 3)
                                    scoreText = when {
                                        average < 70 -> "Siswa perlu diberi soal tambahan"
                                        average >= 70 -> "Siswa mengerti pembelajaran"
                                        else -> ""
                                    }

                                    currScore1 = score1
                                    currScore2 = score2
                                    currScore3 = score3
                                    showButton = true
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
                            enabled = score1.isNotBlank() && score2.isNotBlank() && score3.isNotBlank()
                        )
                        {
                            Text(text = "CALCULATE AVERAGE")
                        }

                        if (showButton) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(scoreText)
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
                                    text = "Average Score: $average",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }

                        if (currScore1 != score1) {
                            showButton = false
                        }
                        if (currScore2 != score2) {
                            showButton = false
                        }
                        if (currScore3 != score3) {
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
fun isValidScore(input: String): Boolean {
    val numberPattern = Pattern.compile("^(?:100|[1-9]?[0-9])$")
    return numberPattern.matcher(input).matches()
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScorePreview() {
    Score()
}