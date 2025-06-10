package com.lucwaw.ludigrid.domain

import com.lucwaw.ludigrid.util.DateUtil.Companion.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class Comment(
    val id : String = "",
    val comment: String = "",
    val rating: Double = -1.0,
    val author : Author = Author(),
    val publishDate: LocalDate = LocalDateTime.now().date,
)