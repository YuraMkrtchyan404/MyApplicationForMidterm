package com.example.myapplicationformidterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationformidterm.ui.theme.MyApplicationForMidtermTheme
import java.lang.Math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationForMidtermTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BullsEyeGame()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BullsEyeGame() {
    var targetValue by remember { mutableStateOf((0..100).random()) }
    var sliderValue by remember { mutableStateOf(50f) }
    var score by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bull's Eye Game",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),
        )

        Text(text = "Move the slider as close as you can to: $targetValue")

        Slider(
            value = sliderValue,
            onValueChange = { newValue -> sliderValue = newValue },
            valueRange = 0f..100f
        )

        Button(
            onClick = {
                val difference = kotlin.math.abs(targetValue - sliderValue.toInt())
                val points = when {
                    difference <= 3 -> {
                        comment = "Perfect! You get 5 points"
                        5
                    }

                    difference <= 8 -> {
                        comment = "Close enough, you get 1 point"
                        1
                    }

                    else -> {
                        comment = "Try again to get closer"
                        0
                    }
                }
                score += points
                targetValue = (0..100).random()
                sliderValue = 50f
            }
        ) {
            Text("Hit Me!")
        }

        Text(text = "Your Score: $score")
        Text(
            text = comment,
            color = Color.Gray
        )
    }
}
