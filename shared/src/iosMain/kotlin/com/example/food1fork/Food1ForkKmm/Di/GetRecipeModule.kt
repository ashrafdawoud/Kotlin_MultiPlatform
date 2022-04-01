package com.example.food1fork.Food1ForkKmm.Di

import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_Details.GetRecipe

class GetRecipeModule(
    val cacheModule: CacheModule
) {
    val getRecipe:GetRecipe by lazy {
        GetRecipe(
            recipeCashInterface = cacheModule.recipeCacheInterface
        )
    }
}