package com.lucwaw.ludigrid.domain

import com.lucwaw.ludigrid.util.DateUtil.Companion.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class Post(
    val id: String = "",
    val title: String = "",
    val author: Author = Author(),
    val description: String = "",
    val image: String = "",
    val publishDate: LocalDate = LocalDateTime.now().date,
)