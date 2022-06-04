package uz.gita.pizzaappdemo.mobdev20.domain.repository.home

import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData

interface HomeRepository {
    // get category
    suspend fun getCategory(
        success: (List<CategoryData>) -> Unit,
        failure: (Throwable) -> Unit
    )

    // get foods
    suspend fun getFood(
        success: (List<FoodData>) -> Unit,
        failure: (Throwable) -> Unit
    )
}