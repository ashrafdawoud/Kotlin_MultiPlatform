package com.example.food1fork.android.Presentation.navigation.RecipeListScreen.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun  FoodCategoryChips(
    category:String,
    isSelected:Boolean = false,
    onCategorySelected:(String)->Unit
){
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        color = if (isSelected) Color.LightGray else MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium
    ) {
        Row (
            modifier = Modifier.clickable {
                onCategorySelected(category)
            }
                ){
            Text(
                text = category,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

    }
}