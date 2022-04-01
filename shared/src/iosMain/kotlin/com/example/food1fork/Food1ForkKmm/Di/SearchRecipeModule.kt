package com.example.food1fork.Food1ForkKmm.Di

import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_List.SearchRecipes

class SearchRecipeModule (
    val networkModule: NetworkModule,
    val cacheModule: CacheModule
        ){

    val searchRecipes:SearchRecipes by lazy {
        SearchRecipes(
            recipeInterface = networkModule.recipeInterface,
            recipeCachInterface = cacheModule.recipeCacheInterface
        )
    }
}