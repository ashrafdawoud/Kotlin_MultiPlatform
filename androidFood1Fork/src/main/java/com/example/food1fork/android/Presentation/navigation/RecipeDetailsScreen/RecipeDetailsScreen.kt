package com.example.food1fork.android.Presentation.navigation.RecipeListScreen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeDetailsScreen.RecipeDetailsEvents
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeDetailsScreen.RecipedetailsState
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.RecipeListEvents
import com.example.food1fork.android.Presentation.Componnents.RECIPE_IMAGE_HIGHT
import com.example.food1fork.android.Presentation.Componnents.RecipeImage
import com.example.food1fork.android.Presentation.Theme.AppTheme
import com.example.food1fork.android.Presentation.navigation.RecipeDetailsScreen.Components.LoadingRecipeShimmer
import com.example.food1fork.android.Presentation.navigation.RecipeDetailsScreen.Components.RecipeView
import com.example.food1fork.android.Presentation.navigation.RecipeListScreen.Components.RecipeCard
import dagger.Provides

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class, kotlin.ExperimentalStdlibApi::class)
@Composable
fun RecipeDetailsScreen (
    state:RecipedetailsState,
    events: (RecipeDetailsEvents)->Unit
){
    AppTheme(
        displayProgressBar = state.isLoading,
        queue = state.queue,
        onRemoveHeadMessage = {
            events(RecipeDetailsEvents.OnRemoveHeadMessageFromQueue)
        }
        ) {
        if(state.recipe == null && state.isLoading){
            LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HIGHT.dp)
        }else if(state.recipe == null){
            Text("Unable to get the details of this recipe...")
        }
        else{
            RecipeView(recipe = state.recipe!!)
        }
    }
}