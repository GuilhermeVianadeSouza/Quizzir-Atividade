package com.aulaandroid.quizgameaplication.quiz

data class Perguntas(
    val textoDaPergunta: String,
    val alternativas: List<String>,
    val indiceRespostaCorreta: Int
) {

}