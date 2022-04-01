package com.example.food1fork.Food1ForkKmm.Domain.Model

sealed class UIComponentType{
    // اي نوع ممكن تستخدمه للايرور
    object Dialog: UIComponentType()
    object SnackBar: UIComponentType()

    object None: UIComponentType()
}
