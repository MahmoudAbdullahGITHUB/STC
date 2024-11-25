package com.example.stc.di

import com.example.stc.data.remote.MarvelApiService
import com.example.stc.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Singleton
//    @Provides
//    fun provideCharactersRepository(
//        marvelApiService: MarvelApiService,
//        mapper: RecipeDtoMapper,
//    ): CharactersRepository {
//        return RecipeRepositoryImpl(recipeService, mapper)
//    }
}