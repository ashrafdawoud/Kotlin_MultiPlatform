package com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem

class FoodCategoryUtil {
    fun getAllFoodCategories(): List<FoodCategoryEnum> {
        return listOf(
            FoodCategoryEnum.ERROR,
            FoodCategoryEnum.CHICKEN,
            FoodCategoryEnum.BEEF,
            FoodCategoryEnum.SOUP,
            FoodCategoryEnum.DESSERT,
            FoodCategoryEnum.VEGETARIAN,
            FoodCategoryEnum.MILK,
            FoodCategoryEnum.VEGAN,
            FoodCategoryEnum.PIZZA,
            FoodCategoryEnum.DONUT
        )
    }
    fun getFoodCategory(value: String): FoodCategoryEnum? {
        val map = FoodCategoryEnum.values().associateBy(FoodCategoryEnum::value)
        return map[value]
    }

}