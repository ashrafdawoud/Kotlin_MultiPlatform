package com.example.food1fork.Food1ForkKmm.Interactors.Recipe_Details

import com.example.food1fork.Food1ForkKmm.DataSource.Cashe.RecipeCacheInterface
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.food1fork.Food1ForkKmm.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe constructor(
    val recipeCashInterface: RecipeCacheInterface
){
    fun excute(id:Int):Flow<DataState<RecipeModel>> = flow {
        emit(DataState.loading<RecipeModel>())
        try {
            val recipeModel=recipeCashInterface.get(id)
            emit(DataState.data(message = null,data = recipeModel))
        }catch (e:Exception){
            emit(DataState.error<RecipeModel>(e.message.toString()?:"UNKNOWN ERROR"))
        }
    }
}