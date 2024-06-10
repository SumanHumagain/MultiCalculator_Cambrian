package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

@Composable
fun CalcView(){

}

@Composable
fun CalcRow(){

}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(text = display.value)
}


@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) {
    Button(
        onClick = {
            // Append the number to the display value
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
            // Logic to handle the operation and update the display
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