package uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home

import androidx.lifecycle.LiveData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData

interface HomeViewModel {

    val foodsLiveData: LiveData<List<FoodData>>
    val categoryLiveData: LiveData<List<CategoryData>>
    val netWorkLiveData:LiveData<String>
}