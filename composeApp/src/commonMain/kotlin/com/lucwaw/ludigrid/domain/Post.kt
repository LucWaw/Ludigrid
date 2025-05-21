package com.lucwaw.ludigrid.domain

data class Post(
    val id: String = "",
    val title: String = "",
    val author: Author = Author(),
    val description: String = "",
    val image: String = "",
    //val publishDate: LocalDate = LocalDate.now(),
)