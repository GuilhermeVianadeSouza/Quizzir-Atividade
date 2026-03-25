package com.aulaandroid.quizgameaplication.screens

import android.R.id.bold
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aulaandroid.quizgameaplication.R
import com.aulaandroid.quizgameaplication.componentes.botaoAlternativa
import com.aulaandroid.quizgameaplication.quiz.QuizViewModel

@Composable
fun ModeloPerguntas(
    viewModel: QuizViewModel,
    navController: NavController
) {
    val indice by viewModel.indiceAtual.collectAsState()
    val perguntaDaVez by viewModel.perguntaAtual.collectAsState()
    val mostrarCores by viewModel.mostrarCores.collectAsState()
    val indiceSelecionado by viewModel.indiceSelecionado.collectAsState()
    val jogoFinalizado by viewModel.jogoFinalizado.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Magenta),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.quiz),
            contentDescription = "Imagem Quiz",
            modifier = Modifier.size(120.dp).
            padding(8.dp)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.Green),
        ) {
            Text(text = "Pergunta ${indice + 1} de 3",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold)
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = perguntaDaVez.textoDaPergunta)

                Spacer(modifier = Modifier.height(16.dp))

                perguntaDaVez.alternativas.forEachIndexed { indice, textoDaOpcao ->

                    val corDinamica = if (mostrarCores) {
                        when {
                            indice == perguntaDaVez.indiceRespostaCorreta -> Color.Green
                            indice == indiceSelecionado -> Color.Red
                            else -> Color.Gray
                        }
                    } else {
                        Color.Gray
                    }

                    botaoAlternativa(
                        texto = textoDaOpcao,
                        corDeFundo = corDinamica,
                        onClick = {
                            viewModel.responder(indice)
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect(jogoFinalizado) {
        if (jogoFinalizado){
            navController.navigate("Tela-Resultado"){
                popUpTo("Tela-Perguntas") { inclusive = true}
            }
        }
    }
}