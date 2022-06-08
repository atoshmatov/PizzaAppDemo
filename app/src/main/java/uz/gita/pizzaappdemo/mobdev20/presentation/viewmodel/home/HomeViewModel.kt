package uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home

import androidx.lifecycle.LiveData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.AdsData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData

interface HomeViewModel {

    //event
    val foodsLiveData: LiveData<List<FoodData>>
    val categoryLiveData: LiveData<List<CategoryData>>
    val adsLiveData: LiveData<List<AdsData>>
    val progressLiveData: LiveData<Boolean>
    val netWorkLiveData: LiveData<String>
    val openDrawLiveData: LiveData<Unit>
    val closeDrawLiveData: LiveData<Unit>
    val openDialogLiveData: LiveData<Unit>

    //action
    fun openDraw()
    fun closeDraw()
    fun openDialog()
}