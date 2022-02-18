package com.livin.starwars.data.remote

import com.livin.starwars.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun searchPeople(@Query("search") search:String): PeopleSearchDto

    @GET("people/{id}")
    suspend fun getPeopleDetails(@Path("id") id: String): PeopleDetailsDto

    @GET("films/{id}")
    suspend fun getFilmDetails(@Path("id") id: String): FilmDetailsDto

    @GET("planets/{id}")
    suspend fun getPlanetDetails(@Path("id") id: String): PlanetDto

    @GET("species/{id}")
    suspend fun getSpeciesDetails(@Path("id") id: String): SpeciesDto

}