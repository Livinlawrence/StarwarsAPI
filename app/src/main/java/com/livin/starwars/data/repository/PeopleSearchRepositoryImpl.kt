package com.livin.starwars.data.repository

import com.livin.starwars.common.Resource
import com.livin.starwars.data.local.PeopleDao
import com.livin.starwars.data.remote.StarWarsApi
import com.livin.starwars.domain.entity.People
import com.livin.starwars.domain.repository.PeopleSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class PeopleSearchRepositoryImpl(private val api: StarWarsApi, private val dao: PeopleDao) :
    PeopleSearchRepository {

    override fun peopleSearch(word: String): Flow<Resource<List<People>>> = flow {
        emit(Resource.Loading())
        val localPeople = dao.searchPeople(word).map {
            it.toPeople()
        }
        emit(Resource.Loading(localPeople.sortedBy { it.name }))
        try {
            val response = api.searchPeople(word)
            if(response.results.isEmpty()){
                emit(
                    Resource.Error(
                        data = localPeople,
                        message = "No results found for $word"
                    )
                )
                return@flow
            }
            dao.deletePeople(response.results.map { it.name })
            dao.insertPeople(response.results.map { it.toPeopleEntity() })
            val newPeople = dao.searchPeople(word).map { it.toPeople() }
            emit(Resource.Success( newPeople.sortedBy { it.name }))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    data = localPeople,
                    message = "Something went wrong with network call"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(data = localPeople, message = "No internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message?:"error"))
        }
    }.flowOn(Dispatchers.IO)
}