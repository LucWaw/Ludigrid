package com.lucwaw.ludigrid.domain

data class Comment(
    val id : String = "",
    val comment: String = "",
    val rating: Double = -1.0,
    val author : Author = Author()
    //val publishDate: LocalDate = LocalDate.now(),
)