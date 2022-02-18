package com.livin.starwars.di

import android.app.Application
import androidx.room.Room
import com.livin.starwars.BuildConfig
import com.livin.starwars.common.Constants
import com.livin.starwars.data.local.StarWarsAppDatabase
import com.livin.starwars.data.remote.StarWarsApi
import com.livin.starwars.data.repository.PeopleSearchRepositoryImpl
import com.livin.starwars.domain.repository.PeopleSearchRepository
import com.livin.starwars.domain.usecase.PeopleSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): StarWarsAppDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            StarWarsAppDatabase::class.java,
        ).build()
    }

    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): StarWarsApi = retrofit.create()

}