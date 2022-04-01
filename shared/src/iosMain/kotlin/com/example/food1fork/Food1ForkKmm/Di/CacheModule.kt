package com.example.food1fork.Food1ForkKmm.Di

import com.example.food1fork.Food1ForkKmm.DataSource.Cashe.*
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Utils.DatetimeUtil

class CacheModule {
    private val driverFactory:DriverFactory by lazy { DriverFactory() }

    private val recipeDatabase:RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory).createDatabase()
    }

    val recipeCacheInterface:RecipeCacheInterface by lazy {
        RecipeCasheImp(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }
}