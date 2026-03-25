package com.aulaandroid.quizgameaplication.quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel : ViewModel() {
    private val _nomeJogador = MutableStateFlow("")

    val nomeJogador: StateFlow<String> = _nomeJogador.asStateFlow()

    fun atualizarNome(novoNome: String){
        _nomeJogador.value = novoNome
    }
}