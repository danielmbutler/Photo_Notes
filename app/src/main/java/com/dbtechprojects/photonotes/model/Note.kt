package com.dbtechprojects.photonotes.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note (
    val id: Int,
    val note: String,
    val title: String,
    val dateUpdated: String = getDateCreated(),
    val imageUris: List<String>? = null
)

fun getDateCreated(): String {
    return LocalDateTime.now().minusDays((0..200).random().toLong()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun Note.getDay(): String{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(this.dateUpdated,formatter ).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}