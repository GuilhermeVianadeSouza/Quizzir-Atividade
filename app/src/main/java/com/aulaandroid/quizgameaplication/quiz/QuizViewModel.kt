package com.aulaandroid.quizgameaplication.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private val _nomeJogador = MutableStateFlow("")

    val nomeJogador: StateFlow<String> = _nomeJogador.asStateFlow()

    fun atualizarNome(novoNome: String){
        _nomeJogador.value = novoNome
    }

    private val pergunta1 = Perguntas(
        textoDaPergunta = "Qual o nome técnico do gorila?",
        alternativas = listOf("Simios pato nara", "Gorila yutrusa angor", "Gorilla gorilla gorilla", "Gorilla inter contin"),
        indiceRespostaCorreta = 2
    )

    private val pergunta2 = Perguntas(
        textoDaPergunta = "Qual foi o maior conjunto de seres no reino animalia?",
        alternativas = listOf("Cordados", "Cnidários", "Poriferos", "Artropode"),
        indiceRespostaCorreta = 3
    )

    private val pergunta3 = Perguntas(
        textoDaPergunta = "Qual foi o primeiro dinossauro descoberto no planeta terra?",
        alternativas = listOf("Tiranossauros rex", "Triceratops Horridus", "Espinossauros Aegiptycus", "Megalossauro Bucklandii"),
        indiceRespostaCorreta = 3
    )

    val gavetarPerguntas = listOf(pergunta1, pergunta2, pergunta3)

    private val _perguntaAtual = MutableStateFlow(gavetarPerguntas[0])
    val perguntaAtual = _perguntaAtual.asStateFlow()

    private val _mostrarCores = MutableStateFlow(false)
    val mostrarCores = _mostrarCores.asStateFlow()

    private val _pontuacaoFinal = MutableStateFlow(0)
    val pontuacaoFinal = _pontuacaoFinal.asStateFlow()

    private val _indiceAtual = MutableStateFlow(0)
    val indiceAtual = _indiceAtual.asStateFlow()

    private val _jogoFinalizado = MutableStateFlow(false)
    val jogoFinalizado = _jogoFinalizado.asStateFlow()

    private val _indiceSelecionado = MutableStateFlow<Int?>(null)
    val indiceSelecionado = _indiceSelecionado.asStateFlow()


    private fun avancarPergunta() {
        if (_indiceAtual.value < gavetarPerguntas.size - 1) {
            _indiceAtual.value += 1

            _perguntaAtual.value = gavetarPerguntas[_indiceAtual.value]
        } else {
            _jogoFinalizado.value = true
        }
    }
    fun responder(indiceClicado: Int){
        if (_mostrarCores.value) return

        _indiceSelecionado.value = indiceClicado
        if (indiceClicado == _perguntaAtual.value.indiceRespostaCorreta){
            _pontuacaoFinal.value += 1
        }
        _mostrarCores.value = true

        viewModelScope.launch{
            delay(500)
            _mostrarCores.value = false
            _indiceSelecionado.value = null
            avancarPergunta()
        }
    }

    fun reiniciarJogo() {
        _indiceAtual.value = 0
        _pontuacaoFinal.value = 0
        _jogoFinalizado.value = false
        _perguntaAtual.value = gavetarPerguntas[0]
    }
}