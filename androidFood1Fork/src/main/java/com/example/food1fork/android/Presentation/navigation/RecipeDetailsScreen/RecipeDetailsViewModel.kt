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
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Model.UIComponentType
import com.example.food1fork.Food1ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import com.example.food1fork.Food1ForkKmm.Domain.Utils.Queue
import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_Details.GetRecipe
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeDetailsScreen.RecipeDetailsEvents
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeDetailsScreen.RecipedetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailsViewModel @Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
) : ViewModel() {

    val state: MutableState<RecipedetailsState> = mutableStateOf(RecipedetailsState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTreggerEvents(RecipeDetailsEvents.onLoadRecipe(recipeId))
        }
    }

    fun onTreggerEvents(event: RecipeDetailsEvents) {
        when (event) {
            is RecipeDetailsEvents.onLoadRecipe -> {
                getrecipe(event.recipeId)
            }
            RecipeDetailsEvents.OnRemoveHeadMessageFromQueue ->{
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .description("Invalid Event")
                        .uiComponentType(UIComponentType.Dialog)
                )
            }
        }
    }

    fun getrecipe(id: Int) {

        println("called")
        viewModelScope.launch {
            getRecipe.excute(id).onEach {
                //println("is loading = "+it.isLoading)
                state.value = state.value.copy(isLoading = it.isLoading)
                it.data?.let {
                    println("getReicipe    :   " + it)
                    state.value = state.value.copy(recipe = it)
                }
                it.message?.let {
                    appendToMessageQueue(it)
                }
            }.launchIn(viewModelScope)
        }
    }

    fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil().doesMessageIsAlreadyExistOnQueue(
             state.value.queue,
             messageInfo.build()
        )){
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }

    }
    fun removeHeadMessage(){
        try {
            val queue = state.value.queue
            queue.remove()
            state.value=state.value.copy(queue = Queue(mutableListOf()))//تغيير الداتا في ال كيو او الليست مش بيعمل recompose uahk عشان كدا بنخليها فاضيه وبعدين نضيف داتا فيها
            state.value=state.value.copy(queue =queue)
        }catch (e:Exception){}
    }
}

