package com.example.food1fork.android.Presentation.navigation.RecipeListScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_List.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
):ViewModel() {
    val recipelist: MutableState<List<RecipeModel>?> = mutableStateOf(null)

    init {
        excute()
    }
    fun excute(){
        searchRecipes.excute(1,"chicken")
            .onEach {
                println("aashraaf :${it.isLoading}")
                it.data?.let {
                    recipelist.value=it
                    println("aashraaf :"+it.toString())
                }
                it.message?.let {
                    println("aashraaf :"+it.toString())
                }
            }.launchIn(viewModelScope)
    }

}