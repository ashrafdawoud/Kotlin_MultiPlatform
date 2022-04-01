package com.example.food1fork.Food1ForkKmm.Di

import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorClientFactory
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterfaceImp

class NetworkModule {

    val recipeInterface:RecipeInterface by lazy {
        RecipeInterfaceImp(
            client = KtorClientFactory().build(),
            baselink = RecipeInterfaceImp.BASEURL
        )
    }
}