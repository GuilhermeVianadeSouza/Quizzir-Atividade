package com.aulaandroid.quizgameaplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aulaandroid.quizgameaplication.R
import com.aulaandroid.quizgameaplication.quiz.QuizViewModel

@Composable
fun TelaResultadoScreen(
    viewModel: QuizViewModel,
    navController: NavController
) {
    val pontuacao by viewModel.pontuacaoFinal.collectAsState()
    val nome by viewModel.nomeJogador.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.quiz),
            contentDescription = "Imagem Quiz",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp)
        )


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Card(

                    colors = CardDefaults.cardColors(containerColor = Color.Green),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Bom trabalho!",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }


                Text(
                    text = "Você, $nome, acertou $pontuacao de 3!",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Button(
            onClick = {
                viewModel.reiniciarJogo()

                navController.navigate("Tela-Inicial") {
                    popUpTo(0)
                }
            },

            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "JOGAR NOVAMENTE",
                fontSize = 22.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}