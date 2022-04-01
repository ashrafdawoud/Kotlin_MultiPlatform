package com.example.food1fork.Food1ForkKmm.Interactors.Recipe_List

import com.example.food1fork.Food1ForkKmm.DataSource.Cashe.RecipeCacheInterface
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Model.UIComponentType
import com.example.food1fork.Food1ForkKmm.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    val recipeInterface: RecipeInterface,
    val recipeCachInterface: RecipeCacheInterface
) {
    fun excute(page:Int,query:String):Flow<DataState<List<RecipeModel>>> = flow{
        emit(DataState.loading())
        try {
            val recipes=recipeInterface.search(page,query)
            kotlinx.coroutines.delay(500)
            if (query == ("error")){
                throw Exception(" Search Faild ..")
            }
            recipeCachInterface.insert(recipes)
            val recipeResult=if (query.isBlank()){
                recipeCachInterface.getAll(page)
            }else{
                recipeCachInterface.search(query, page)
            }
            emit(DataState.data(message = null,data = recipeResult))
        }catch (e:Exception){
            emit(DataState.error<List<RecipeModel>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.ERROR")
                    .title("Error")
                    .description(e.message?:"UnKnownErroe")
                    .uiComponentType(UIComponentType.Dialog)))
        }
    }
}