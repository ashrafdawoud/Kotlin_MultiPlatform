package com.example.food1fork.Food1ForkKmm.Interactors.Recipe_Details

import com.example.food1fork.Food1ForkKmm.DataSource.Cashe.RecipeCacheInterface
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Model.UIComponentType
import com.example.food1fork.Food1ForkKmm.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe constructor(
    val recipeCashInterface: RecipeCacheInterface
){
    fun excute(id:Int):Flow<DataState<RecipeModel>> = flow {
        emit(DataState.loading())
        try {
            val recipeModel=recipeCashInterface.get(id)
            kotlinx.coroutines.delay(500)
            emit(DataState.data(message = null,data = recipeModel))
        }catch (e:Exception){
            emit(DataState.error<RecipeModel>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.ERROR")
                    .title("Error")
                    .description(e.message?:"UnKnownErroe")
                    .uiComponentType(UIComponentType.Dialog)))
        }
    }
}