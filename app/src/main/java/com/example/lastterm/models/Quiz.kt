package com.example.lastterm.models
data class Quiz(
    var id: String = "",
    var title: String = "",
    var questions: MutableMap<String , Question> = mutableMapOf()
//    var questions: MutableMap<String , ques> = mutableMapOf()
)