package com.example.food1fork.android.Presentation.navigation.RecipeListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.FoodCategoryUtil
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.RecipeListEvents
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.RecipeListState
import com.example.food1fork.android.Presentation.Theme.AppTheme
import com.example.food1fork.android.Presentation.navigation.RecipeListScreen.Components.RecipeList
import com.example.food1fork.android.Presentation.navigation.RecipeListScreen.Components.SearchAppBar

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeListScreen(
    state:RecipeListState,
    onTriggerEvent:(RecipeListEvents)->Unit,
    onselectRecipe: (Int) -> Unit
) {
    val categories= remember {
        FoodCategoryUtil().getAllFoodCategories()
    }
    AppTheme(
        displayProgressBar = state.isloading,
        queue = state.queue,
        onRemoveHeadMessage = {
            onTriggerEvent(RecipeListEvents.OnRemoveHeadMessageFromQueue)
        }
    ) {
       Scaffold (
           modifier = Modifier.fillMaxWidth(),
           topBar = {
               SearchAppBar(
                   query = state.query ,
                   categories = categories,
                   onSelectFoodCategory={
                     onTriggerEvent(RecipeListEvents.OnSelectCategory(it))
                   },
                   selectedCategory = state.selectedCategory,
                   onQueryChanged = {
                       onTriggerEvent(RecipeListEvents.OnQueryChanged(it))
                   },
                   onExcuteSearch = {
                       onTriggerEvent(RecipeListEvents.ExcuteSearch)
                   }
               )
           },


       ){
           RecipeList(
               isloading = state.isloading,
               recipes = state.recipes,
               state.page,
               onTriggerNextPage= {
                   onTriggerEvent(RecipeListEvents.NextRecipe)
               },
               onclick = onselectRecipe)
       }
    }

}