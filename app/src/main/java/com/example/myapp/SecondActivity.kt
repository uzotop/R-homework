package com.example.myapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

class SecondActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FragmentContainer()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(android.R.id.content, FeedbackFragment())
            }
        }
    }
}

@Composable
fun FragmentContainer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000033)), // Цвет фона #000033
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                // Переход на следующее действие или фрагмент
            },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0x33808093)), // Цвет кнопки #808093 с 20% прозрачностью
            shape = RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomStartPercent = 50,
                bottomEndPercent = 50
            )
        ) {
            Text(
                text = "Сообщить о проблеме",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
