package com.aulaandroid.quizgameaplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aulaandroid.quizgameaplication.quiz.QuizViewModel
import com.aulaandroid.quizgameaplication.screens.TelaInicialScreen
import com.aulaandroid.quizgameaplication.ui.theme.QuizGameAplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizGameAplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "Tela-Inicial",
                        modifier = Modifier.padding(innerPadding),
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(2000)
                            ) + fadeOut(animationSpec = tween (500))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween (2000)
                            ) + fadeIn(animationSpec = tween(2000))
                        }
                    ) {
                        composable(
                            route = "Tela-Inicial",
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween (2000)
                                )
                            }){

                            val viewModel: QuizViewModel = viewModel()
                            val nome by viewModel.nomeJogador.collectAsState()

                            TelaInicialScreen(
                                navController = navController,
                                label = "Digite o seu nome",
                                placeholder = "Digites o seu nome completo",
                                keyboardType = KeyboardType.Text,
                                value = nome,

                                atualizarValor = { novoTexto ->
                                  viewModel.atualizarNome(novoTexto)
                                }
                            )
                        }
                        composable(
                            route = "Primeira-pergunta",
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(2000)
                                )
                            }
                        ){
                            
                        }
                    }
                }
            }
        }
    }
}
