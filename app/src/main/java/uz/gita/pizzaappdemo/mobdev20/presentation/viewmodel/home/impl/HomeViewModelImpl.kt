package uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData
import uz.gita.pizzaappdemo.mobdev20.domain.usecase.home.HomeUseCase
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl
@Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel(), HomeViewModel {
    override val foodsLiveData = MutableLiveData<List<FoodData>>()
    override val categoryLiveData = MutableLiveData<List<CategoryData>>()
    override val netWorkLiveData = MutableLiveData<String>()

    init {
        loadCategory()
        loadFood()
    }

    private fun loadCategory() {
        homeUseCase.getCategoryData().onEach { result ->
            result.onSuccess { data -> categoryLiveData.value = data }
            result.onFailure { failure -> netWorkLiveData.value = failure.message }
        }.launchIn(viewModelScope)
    }

    private fun loadFood() {
        homeUseCase.getFoodsData().onEach { result ->
            result.onSuccess { data -> foodsLiveData.value = data }
            result.onFailure { failure -> netWorkLiveData.value = failure.message }
        }.launchIn(viewModelScope)
    }
}