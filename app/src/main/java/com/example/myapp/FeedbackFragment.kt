package com.example.myapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.fragment.app.Fragment
import androidx.compose.foundation.text.ClickableText

class FeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FeedbackScreen {
                    activity?.onBackPressed()
                }
            }
        }
    }
}

@Composable
fun FeedbackScreen(onBackPressed: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000033)) // Фон цвета #000033
    ) {
        TopAppBar(
            title = { Text(text = "Обратная связь", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            backgroundColor = Color(0xFF000033) // Цвет верхнего бара
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           // Text(text = "Обратная связь", fontSize = 20.sp, color = Color.White)
           // Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Версия приложения", color = Color.White)
            Text(text = "3.10.7", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Номер сборки", color = Color.White)
            Text(text = "300.183", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Версия ОС", color = Color.White)
            Text(text = "14.6", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Сообщение отправлено", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0x33808093)), // Полупрозрачный цвет кнопки #80809333
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
            Spacer(modifier = Modifier.height(16.dp))

            val annotatedText = buildAnnotatedString {
                append("АО «ЭР-Телеком Холдинг»\n")
                pushStringAnnotation(tag = "PHONE", annotation = "tel:+73420000000")
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("+7 342 000 00 00\n")
                }
                pop()
                pushStringAnnotation(tag = "EMAIL", annotation = "mailto:help@domru.ru")
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("help@domru.ru\n")
                }
                pop()
                pushStringAnnotation(tag = "URL", annotation = "https://www.ertelecom.ru")
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("www.ertelecom.ru")
                }
                pop()
            }

            ClickableText(
                text = annotatedText,
                modifier = Modifier.padding(8.dp),
                style = TextStyle(color = Color.White, fontSize = 14.sp, textAlign = TextAlign.Center),
                onClick = { offset ->
                    annotatedText.getStringAnnotations(start = offset, end = offset).firstOrNull()?.let { annotation ->
                        when (annotation.tag) {
                            "PHONE" -> {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse(annotation.item)
                                }
                                context.startActivity(intent)
                            }
                            "EMAIL" -> {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse(annotation.item)
                                }
                                context.startActivity(intent)
                            }
                            "URL" -> {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse(annotation.item)
                                }
                                context.startActivity(intent)
                            }
                        }
                    }
                }
            )
        }
    }
}
