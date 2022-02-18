package com.livin.starwars.data.repository

import com.livin.starwars.common.Resource
import com.livin.starwars.common.getIdFromUrl
import com.livin.starwars.data.remote.StarWarsApi
import com.livin.starwars.domain.entity.Film
import com.livin.starwars.domain.entity.PeopleDetails
import com.livin.starwars.domain.repository.PeopleDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PeopleDetailsRepositoryImpl(private val api: StarWarsApi) :
    PeopleDetailsRepository {

    override fun getPeopleDetails(peopleId: String): Flow<Resource<PeopleDetails>> = flow {
        emit(Resource.Loading())
        try {
            val films =  mutableListOf<Film>()
            val languages =  mutableListOf<String>()
            val response = api.getPeopleDetails(peopleId)
            val planet = api.getPlanetDetails(response.homeworld.getIdFromUrl()).name
            response.films.onEach {
                val film = api.getFilmDetails(it.getIdFromUrl()).toFilm()
                films.add(film)
            }
            response.species.onEach {
                val language = api.getSpeciesDetails(it.getIdFromUrl()).language
                languages.add(language)
            }
            emit(Resource.Success(response.toPeopleDetails(planet,films,languages)))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message ?: "error"))
        } catch (e: IOException) {
            emit(Resource.Error(data = null, message = "No internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "error"))
        }
    }
}