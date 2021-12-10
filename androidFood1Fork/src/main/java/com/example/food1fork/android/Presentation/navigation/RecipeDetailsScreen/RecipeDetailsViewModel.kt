package com.example.food1fork.android.Presentation.navigation.RecipeDetailsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Utils.DatetimeUtil
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterfaceImp
import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_Details.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailsViewModel @Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
) : ViewModel() {

    val recipe: MutableState<RecipeModel?> = mutableStateOf(null)
    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
        }
    }
    fun getrecipe(id:Int){
        println("called")
        viewModelScope.launch {
            getRecipe.excute(id).onEach {
                it.data?.let {
                    println("getReicipe    :   "+it)
                    recipe.value=it
                }
                it.message?.let {
                    println("getReicipe error   :   " + it)
                }
            }.launchIn(viewModelScope)
        }
    }
}

