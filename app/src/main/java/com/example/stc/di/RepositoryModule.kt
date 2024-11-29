package com.example.stc.di


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