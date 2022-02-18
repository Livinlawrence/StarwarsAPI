package com.livin.starwars.domain.usecase

import com.livin.starwars.common.Resource
import com.livin.starwars.domain.entity.PeopleDetails
import com.livin.starwars.domain.repository.PeopleDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetPeopleDetailsUseCase(private val peopleDetailsRepository: PeopleDetailsRepository) {

    operator fun invoke(id: String): Flow<Resource<PeopleDetails>> {
        return peopleDetailsRepository.getPeopleDetails(id)
    }
}