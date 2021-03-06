package com.example.food1fork.android.Presentation.Theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Utils.Queue
import com.example.food1fork.android.Presentation.Componnents.CircularIndeterminateProgressBar
import com.example.food1fork.android.Presentation.Componnents.ProcessDialogQueue

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
    onRemoveHeadMessage:()->Unit,
    content: @Composable () -> Unit,

) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShaps
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Grey1)
        ){
            ProcessDialogQueue(
                queue = queue,
                onRemoveHeadMessage =onRemoveHeadMessage
            )
            content()
            CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, verticalBias = 0.5f)
        }
    }
}