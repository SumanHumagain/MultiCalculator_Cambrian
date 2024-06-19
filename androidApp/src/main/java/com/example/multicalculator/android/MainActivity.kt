package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multicalculator.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalcView() //call calculator view
                }
            }
        }
    }
}

@Composable
fun CalcView() {
    val displayText = remember { mutableStateOf("0") }
    var leftNumber = rememberSaveable { 0 }
    var rightNumber = rememberSaveable { 0 }
    var operation = rememberSaveable { "" }
    var complete = rememberSaveable { false }

    if (complete && operation.isNotEmpty()) {
        var answer: Int = when (operation) {
            "+" -> leftNumber + rightNumber
            "-" -> leftNumber - rightNumber
            "*" -> leftNumber * rightNumber
            "/" -> if (rightNumber != 0) leftNumber / rightNumber else 0
            else -> 0
        }

        displayText.value = answer.toString()

        }
    else if (operation.isNotEmpty() && !complete) {
                displayText.value = rightNumber.toString()
        }
    else {
        displayText.value = leftNumber.toString()
    }

    Column(
        modifier = Modifier
            .background(Color.LightGray).padding(30.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
            ) {
                CalcDisplay(display = displayText) // Display seciton of calculator
            }
        }

        Row {
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(display = displayText, i, numButtons = 3)
                }
                Row {
                    CalcNumericButton(number = 0, display = displayText) //It create numeric buttons
                    CalcEqualsButton(display = displayText) // Creates equal button
                    }
            }
            Column {//Create 4 operators
                CalcOperationButton(operation = "+", display = displayText)
                CalcOperationButton(operation = "-", display = displayText)
                CalcOperationButton(operation = "*", display = displayText)
                CalcOperationButton(operation = "/", display = displayText)
            }
        }
    }
}




@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons

    Row(
        modifier = Modifier.padding(0.dp)
    ) {
        for (i in startNum until endNum) {
            CalcNumericButton(number = i, display = display)
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth()
    )
}


@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) {
    Button(
        onClick = {
            display.value += number.toString()
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}


@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>) {
    Button(
        onClick = {
            // Logic to update the display text
            display.value += operation
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>){
    Button(
        onClick = {
            display.value = "0"
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}