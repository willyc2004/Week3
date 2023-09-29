package com.example.week3_willy_53.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

fun isValidNumber(input: String): Boolean {
    val numberPattern = Pattern.compile("^[0-9]+$")
    return numberPattern.matcher(input).matches() && input.toInt() >= 0
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriangleArea() {
    var base by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }

    var area = ""

    area = if (isValidNumber(base) && isValidNumber(height)) {
        if (base.isNotEmpty() && height.isNotEmpty()) {
            (0.5 * base.toInt() * height.toInt()).toString()
        } else {
            "0.0"
        }
    } else {
        "0.0"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 10.dp)
                    .rotate(270F),
                tint = Color.Blue
            )


            OutlinedTextField(
                value = base,
                onValueChange = {
                    base = it
                },
                label = { Text("Base", color = Color.Blue) },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Blue,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
            )

            OutlinedTextField(
                value = height,
                onValueChange = {
                    height = it
                },
                label = { Text("Height", color = Color.Blue) },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Blue,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
            )

            Text(
                text = "The Triangle Area is:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = area,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TriangleAreaPreview() {
    TriangleArea()
}