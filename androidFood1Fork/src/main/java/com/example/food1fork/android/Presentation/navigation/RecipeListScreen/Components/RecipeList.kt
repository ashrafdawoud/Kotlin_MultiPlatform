package com.example.food1fork.android.Presentation.navigation.RecipeListScreen.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.android.Presentation.Componnents.RECIPE_IMAGE_HIGHT

@Composable
fun RecipeList(
    isloading:Boolean,
    recipes:List<RecipeModel>,
    page:Int,
    onTriggerNextPage:() -> Unit,
    onclick : (Int)->Unit
) {
    if (isloading&&recipes.isEmpty()){
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HIGHT.dp)
    }else if(recipes.isEmpty()){
        // there is no data
    }else{
        LazyColumn {
            itemsIndexed(
                recipes
            ){index, item ->
                if ((index+1)>=(page*30)&&!isloading){
                    onTriggerNextPage()
                }
                RecipeCard(
                    recipeModel = item,
                    onclik = {
                        onclick(item.id)
                    })
            }
        }
    }
}