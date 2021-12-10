package com.example.food1fork.android.Presentation.navigation.RecipeListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import java.lang.reflect.Modifier

@Composable
fun RecipeListScreen(
    recipeList:List<RecipeModel>?,
     onselectRecipe:(Int) ->Unit
) {
 LazyColumn{
     recipeList?.size?.let { items(it){ index->
             Row(
                 modifier = androidx.compose.ui.Modifier.fillMaxWidth()
                     .clickable {
                         onselectRecipe(recipeList.get(index).id)
                     }
             ) {
                 Text(modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(10.dp),text = "${recipeList.get(index).title}")
             }
         }
     }
 }
}