package com.example.food1fork.android.Presentation.navigation.RecipeListScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Model.NegativeAction
import com.example.food1fork.Food1ForkKmm.Domain.Model.PositiveAction
import com.example.food1fork.Food1ForkKmm.Domain.Model.UIComponentType
import com.example.food1fork.Food1ForkKmm.Domain.Utils.GenericMessageInfoQueueUtil
import com.example.food1fork.Food1ForkKmm.Domain.Utils.Queue
import com.example.food1fork.Food1ForkKmm.Interactors.Recipe_List.SearchRecipes
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.FoodCategoryEnum
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.RecipeListEvents
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
):ViewModel() {
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        excute()
    }
    fun onTrigerEvints(events: RecipeListEvents){
        when (events){
            RecipeListEvents.LoadingRecipe ->{
                excute()
            }
            RecipeListEvents.NextRecipe ->{
                nextPage()
            }
            RecipeListEvents.ExcuteSearch ->{
                excuteserach()
            }
            RecipeListEvents.OnRemoveHeadMessageFromQueue ->{
                removeHeadMessage()
            }
            is RecipeListEvents.OnQueryChanged ->{
                state.value=state.value.copy(query = events.query,selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory ->{
                onSelectCategory(events.categoryEnum)
            }
            else -> {
                GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .description("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .positive(
                        positiveAction = PositiveAction(
                            positiveBtnTxt = "ok",
                            onPositiveAction = {
                                // write code of function here
                            }
                        )
                    )
                    .negative(
                        negativeAction = NegativeAction(
                            negativeBtnTxt = "cancel",
                            onNegativeAction = {
                                // write code of function here
                            }
                        )
                    )
            }
        }
    }
    fun nextPage(){
        state.value=state.value.copy(page = state.value.page+1)
        excute()
    }
    fun onSelectCategory(foodCategoryEnum: FoodCategoryEnum){
        state.value=state.value.copy(selectedCategory = foodCategoryEnum,query = foodCategoryEnum.value,recipes = listOf())
        excute()
    }
    fun excuteserach(){
        state.value=state.value.copy(page = 1,recipes = listOf())
        excute()
    }
    fun excute(){
        searchRecipes.excute(state.value.page,state.value.query)
            .onEach {
                state.value=state.value.copy(isloading = it.isLoading)
                it.data?.let {
                    paggination(it)
                }
                it.message?.let {
                    appendToMessageQueue(it)
                }
            }.launchIn(viewModelScope)
    }
    fun paggination(recipes:List<RecipeModel>){
        val curr:ArrayList<RecipeModel> = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value=state.value.copy(recipes = curr)
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