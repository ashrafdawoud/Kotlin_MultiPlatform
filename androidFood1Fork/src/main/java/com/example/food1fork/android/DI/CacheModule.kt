package com.example.food1fork.android.DI
import com.example.food1fork.Food1ForkKmm.DataSource.Cashe.*
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Utils.DatetimeUtil
import com.example.food1fork.android.BaseAplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseAplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase,
    ): RecipeCacheInterface {
        return RecipeCasheImp(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil(),
        )
    }
}

