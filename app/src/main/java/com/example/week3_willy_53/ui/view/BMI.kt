package com.example.week3_willy_53.ui.view
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week3_willy_53.ui.theme.Purple40
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMI() {
    var weightText by rememberSaveable { mutableStateOf("") }
    var heightText by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val weight = weightText.toDoubleOrNull()
    val height = heightText.toDoubleOrNull()

    val bmiScore = if (weight != null && height != null && height > 0) {
        val heightInMeter = height / 100
        weight / (heightInMeter.pow(2))
    } else {
        null
    }

    val bmiScoreFormat = String.format("%.1f", bmiScore)

    val bmiCategory = when {
        bmiScore != null && bmiScoreFormat.toDouble() < 18.5 -> "Underweight"
        bmiScore != null && bmiScoreFormat.toDouble() < 24.9 -> "Normal Weight"
        bmiScore != null && bmiScoreFormat.toDouble() < 29.9 -> "Overweight"
        bmiScore != null -> "Obese"
        else -> ""
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.Face,
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .padding(top = 10.dp),
                tint = Color.Blue
            )

            OutlinedTextField(
                value = weightText,
                onValueChange = {
                    weightText = it
                },
                label = { Text("Weight in kg", color = Color.Blue) },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Blue,
                ),
                isError = showError
            )
            if (weightText == "") {
                Text(
                    text = "Please enter a valid weight greater than 0",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                    Color.Red
                )
            }

            OutlinedTextField(
                value = heightText,
                onValueChange = {
                    heightText = it
                },
                label = { Text("Height in cm", color = Color.Blue) },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Blue,
                ),
                isError = showError
            )

            if (heightText == "") {
                Text(
                    text = "Please enter a valid height greater than 0",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                    Color.Red
                )
            }

            Button(
                onClick = {
                    if (weight != null && height != null && height > 0) {
                        showDialog = true
                    } else {
                        showError = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                enabled = weightText.isNotBlank() && heightText.isNotBlank()
            )
            {
                Text(text = "Calculate BMI")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        Text("Your BMI Analysis")
                    },
                    text = {
                        Column {
                            Text("Your Weight: $weightText kg")
                            if (height != null) {
                                Text("Your Height: ${height/100} m")
                            }
                            Text("Your BMI Score: $bmiScoreFormat")
                            Text("You are $bmiCategory")
                        }
                    },
                    confirmButton = {
                        TextButton (
                            onClick = {
                                showDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Purple40,
                                contentColor = Color.White),
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMIPreview() {
    BMI()
}