package com.example.food1fork.android.Presentation.Componnents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

const val RECIPE_IMAGE_HIGHT=260
@Composable
fun RecipeImage(
    url: String?,
    discription: String?
) {
    val painter = rememberCoilPainter(request = url)

    when (painter.loadState){
        is ImageLoadState.Error ->{
            Box(modifier = Modifier.fillMaxWidth().height(RECIPE_IMAGE_HIGHT.dp)) {}
        }
        is ImageLoadState.Success->{
            Box {
                Image(
                    painter = painter,
                    contentDescription = discription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(RECIPE_IMAGE_HIGHT.dp)
                    ,
                    contentScale = ContentScale.Crop
                )

            }
        }
        is ImageLoadState.Loading->{
            Box(modifier = Modifier.fillMaxWidth().height(RECIPE_IMAGE_HIGHT.dp)) {}
        }

    }
}