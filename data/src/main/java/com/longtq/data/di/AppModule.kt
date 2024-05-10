package com.longtq.data.di

import android.content.Context
import androidx.room.Room
import com.longtq.data.RepositoryImpl
import com.longtq.data.api.ApiService
import com.longtq.data.database.AppDatabase
import com.longtq.domain.repository.Repository
import com.longtq.domain.usecase.GetListCountriesUseCase
import com.longtq.domain.usecase.SaveListCountriesToLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "countries.db"
        ).build(
        )
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, appDatabase: AppDatabase): Repository {
        return RepositoryImpl(apiService, appDatabase)
    }

    @Provides
    @Singleton
    fun provideGetListCountriesUseCase(countriesRepository: Repository): GetListCountriesUseCase {
        return GetListCountriesUseCase(countriesRepository)
    }

    @Provides
    @Singleton
    fun provideSaveListCountriesToLocalUseCase(countriesRepository: Repository): SaveListCountriesToLocalUseCase {
        return SaveListCountriesToLocalUseCase(countriesRepository)
    }


}