package com.example.food1fork.android.DI

import com.example.food1fork.Food1ForkKmm.DataSource.Cashe.RecipeCacheInterface
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_Details.GetRecipe
import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_List.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {
    @Singleton
    @Provides
    fun provideSeachRecipes(
        recipeInterface: RecipeInterface,
        recipeCacheInterface: RecipeCacheInterface
    ):SearchRecipes{
        return SearchRecipes(recipeInterface,recipeCacheInterface)
    }
    @Singleton
    @Provides
    fun provideSGetRecipe(
        recipeCasheInterface: RecipeCacheInterface
    ): GetRecipe {
        return GetRecipe(recipeCasheInterface)
    }
}