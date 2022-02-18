package com.livin.starwars.di

import com.livin.starwars.domain.repository.PeopleDetailsRepository
import com.livin.starwars.domain.repository.PeopleSearchRepository
import com.livin.starwars.domain.usecase.GetPeopleDetailsUseCase
import com.livin.starwars.domain.usecase.PeopleSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseRepo {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: PeopleSearchRepository): PeopleSearchUseCase {
        return PeopleSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPeopleDetailsUseCase(repository: PeopleDetailsRepository): GetPeopleDetailsUseCase {
        return GetPeopleDetailsUseCase(repository)
    }
}