package uz.gita.pizzaappdemo.mobdev20.domain.usecase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData

interface HomeUseCase {
    fun getCategoryData(): Flow<Result<List<CategoryData>>>
    fun getFoodsData(): Flow<Result<List<FoodData>>>
}