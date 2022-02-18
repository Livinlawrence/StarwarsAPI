package com.livin.starwars.data.remote.dto

data class PeopleSearchDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)