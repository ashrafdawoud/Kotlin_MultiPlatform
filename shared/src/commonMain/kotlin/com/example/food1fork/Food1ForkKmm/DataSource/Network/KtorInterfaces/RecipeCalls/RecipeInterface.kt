package com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls

import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.DataSource.Network.EntityDto.RecipeDTO

interface RecipeInterface {
    suspend fun search(page:Int,query:String):List<RecipeModel>
    suspend fun get(id:Int):RecipeModel
}