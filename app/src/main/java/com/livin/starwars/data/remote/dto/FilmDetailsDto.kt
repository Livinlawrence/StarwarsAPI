package com.livin.starwars.data.remote.dto

import com.livin.starwars.domain.entity.Film

data class FilmDetailsDto(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episode_id: Int,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
){
    fun toFilm(): Film {
        return Film(
            name = title,
            openingCrawl = opening_crawl
        )
    }
}