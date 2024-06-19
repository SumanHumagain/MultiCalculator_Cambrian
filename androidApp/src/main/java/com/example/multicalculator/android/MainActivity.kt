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
import androidx.compose.runtime.*
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
                    CalcView() // Call calculator view
                }
            }
        }
    }
}

@Composable
fun CalcView() {
    val displayText = remember { mutableStateOf("0") }
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }

    if (complete && operation !="") {
        var answer by remember { mutableStateOf(0) }
        when (operation) {
            "+" -> answer =  leftNumber + rightNumber
            "-" -> answer = leftNumber - rightNumber
            "*" -> answer = leftNumber * rightNumber
            "/" -> answer =  leftNumber / rightNumber
        }

        displayText.value = answer.toString()

    } else if (operation !="" && !complete) {
        displayText.value = rightNumber.toString()
    } else {
        displayText.value = leftNumber.toString()
    }

    fun numberPress(btnNum: Int) {
        if (complete) {
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }
        if (operation.isNotBlank() && !complete) {
            rightNumber = rightNumber * 10 + btnNum
        } else if (operation.isBlank() && !complete) {
            leftNumber = leftNumber * 10 + btnNum
        }
    }

    fun operationPress(op: String) {
        if (!complete) {
            operation = op
        }
    }

    fun equalsPress() {
        complete = true
    }

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(30.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
            ) {
                CalcDisplay(display = displayText) // Display section of calculator
            }
        }

        Row {
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(onPress = { number -> numberPress(number) }, startNum = i, numButtons = 3)
                }
                Row {
                    CalcNumericButton(number = 0, onPress = { number -> numberPress(number) })
                    CalcEqualsButton(onPress = { equalsPress() })
                }
            }
            Column {
                CalcOperationButton(operation = "+", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "-", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "*", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "/", onPress = { op -> operationPress(op) })
            }
        }
    }
}

@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons

    Row(
        modifier = Modifier.padding(0.dp)
    ) {
        for (i in startNum until endNum) {
            CalcNumericButton(number = i, onPress = onPress)
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
fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit) {
    Button(
        onClick = {
            onPress(number)
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, onPress: (operation: String) -> Unit) {
    Button(
        onClick = {
            onPress(operation)
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) {
    Button(
        onClick = {
            onPress()
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}
