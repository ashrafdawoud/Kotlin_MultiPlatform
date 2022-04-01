package com.example.food1fork.android.Presentation.Componnents

import androidx.compose.runtime.Composable
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Utils.Queue

@Composable
fun ProcessDialogQueue(
    queue: Queue<GenericMessageInfo>?,
    onRemoveHeadMessage:()->Unit
){
    queue?.peek()?.let { dialogInfo->
        dialogInfo.onDismiss?.let {ondismiss->
            GenericDialog(
                ondesmiss = ondismiss,
                title = dialogInfo.title,
                description=dialogInfo.description,
                positiveAction= dialogInfo.positiveAction,
                negativeAction= dialogInfo.negativeAction,
                onRemoveHeadMessage = onRemoveHeadMessage
            )
        }
    }
}