package com.livin.starwars.domain.usecase

import com.livin.starwars.common.Resource
import com.livin.starwars.domain.entity.People
import com.livin.starwars.domain.repository.PeopleSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PeopleSearchUseCase(private val peopleSearchRepository: PeopleSearchRepository) {

    operator fun invoke(word: String): Flow<Resource<List<People>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return peopleSearchRepository.peopleSearch(word)
    }
}