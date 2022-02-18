package com.livin.starwars.data.repository

import com.livin.starwars.common.Resource
import com.livin.starwars.domain.entity.People
import com.livin.starwars.domain.repository.PeopleSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePeopleSearchRepository : PeopleSearchRepository {

    private val peopleList = mutableListOf<People>()

    override fun peopleSearch(word: String): Flow<Resource<List<People>>> {
        return flow {
            emit(Resource.Success(peopleList.filter { it.name.startsWith(word) }))
        }
    }

    fun insertPeople(people: People) {
        peopleList.add(people)
    }

}