package com.example.food1fork.android.Presentation.navigation.RecipeListScreen.Components

import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.android.Presentation.Componnents.RECIPE_IMAGE_HIGHT
import com.example.food1fork.android.Presentation.Componnents.RecipeImage
import com.example.food1fork.android.Presentation.Theme.QuickSandTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCard(
    recipeModel: RecipeModel,
    onclik: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
            .fillMaxWidth(),
        onClick =onclik,
        elevation = 8.dp
    ) {
        Column {
            RecipeImage(url = recipeModel.featuredImage, discription = recipeModel.title)
            Row (modifier = Modifier.padding(start = 6.dp,end = 6.dp)){
                Text(
                    text = recipeModel.title,
                    modifier = Modifier.fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Start,

                    )
                Text(
                    text = recipeModel.rating.toString(),
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h5,
                )


            }
        }
    }
}