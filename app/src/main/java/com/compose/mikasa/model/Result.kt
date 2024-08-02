package com.compose.mikasa.model

data class Result(
    val id: Int,
    val name: String?,
    val img: String?,
    val alias: List<String>?,
    val species: List<String>?,
    val gender: String?,
    val age: String?,
    val height: String?,
    val relatives: List<Relative>?,
    val birthplace: String?,
    val residence: String?,
    val status: String?,
    val occupation: String?,
    val groups: List<Group>?,
    val roles: List<String>?,
    val episodes: List<String>?
)