package com.compose.mikasa.model

data class Result(
    val age: Int,
    val alias: List<String>,
    val birthplace: String,
    val episodes: List<String>,
    val gender: String,
    val groups: List<Group>,
    val height: String,
    val id: Int,
    val img: String,
    val name: String,
    val occupation: String,
    val relatives: List<Relative>,
    val residence: String,
    val roles: List<String>,
    val species: List<String>,
    val status: String
)