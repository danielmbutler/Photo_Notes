package com.dbtechprojects.photonotes.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

open class Note(
) : RealmObject(){
    @PrimaryKey
    var id =  UUID.randomUUID().toString()
    var note: String = ""
    var title: String = ""
    var dateUpdated: String = getDateCreated()
    var imageUri: String? = null
    var isPlaceholder: Boolean = false
}

fun getDateCreated(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun Note.getDay(): String {
    if (this.dateUpdated.isEmpty()) return ""
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(this.dateUpdated, formatter).toLocalDate()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

val placeHolderList =
    listOf(Note().apply {  title = "No Notes found"; note = "Please create a note"; isPlaceholder = true; id =
        "0"
    })

fun List<Note>?.orPlaceHolderList(): List<Note> {
    return if (this != null && this.isNotEmpty()) {
        this
    } else placeHolderList
}









