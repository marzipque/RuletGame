package com.example.ruletgame.rule_screen

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ruletgame.R
import com.example.ruletgame.ui.theme.Red
import com.example.ruletgame.utils.ValueList


@Composable
fun RuletScreen() {
    var rotationValue by remember {
        mutableFloatStateOf(0f)
    }
    var number by remember {
        mutableIntStateOf(0)
    }
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
            val index = (365f - (rotationValue % 360f)) / (360f / ValueList.list.size)
            Log.d("MyLog", "index: $index")
            number = ValueList.list[index.toInt()]
        }, label = ""
    )
    Log.d("MyLog", "angle: $angle")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = number.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.ruleta),
                contentDescription = "Ruleta",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Arrow",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                rotationValue = ((0..360).random().toFloat() + 720) + angle
            },
            colors = ButtonDefaults.buttonColors(Red)
        ) {
            Text(
                text = "Start",
                color = Color.White
            )
        }
    }
}