package com.aulaandroid.quizgameaplication.screens

import android.R.attr.label
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aulaandroid.quizgameaplication.R
import java.nio.file.WatchEvent

@Composable
fun TelaInicialScreen(
    navController: NavController,
    label: String,
    placeholder: String,
    value: String,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    atualizarValor: (String) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0, 162, 255, 255))
            .padding(all = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(R.drawable.quiz),
            contentDescription = "Imagem Quiz",
            modifier = Modifier.size(120.dp).
            padding(8.dp)
        )

        Text(
            text = "QUIZATRON 3000",
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color.Black
        )

        OutlinedTextField(
            value = value,
            onValueChange = atualizarValor,
            label = {
                Text(
                    text = label
                )
            },
            placeholder = {
                Text(
                    text = placeholder
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            modifier = Modifier,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Yellow
            )
        )

        Button(
            onClick = { navController.navigate("Primeira-pergunta")},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
            ),
            modifier = Modifier.fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "COMECAR!",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 24.sp
            )
        }
    }
}