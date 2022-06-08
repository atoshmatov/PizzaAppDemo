package uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.AdsData
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
    override val adsLiveData = MutableLiveData<List<AdsData>>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val netWorkLiveData = MutableLiveData<String>()
    override val openDrawLiveData = MutableLiveData<Unit>()
    override val closeDrawLiveData = MutableLiveData<Unit>()
    override val openDialogLiveData = MutableLiveData<Unit>()


    init {
        loadCategory()
        loadFood()
        loadAds()
    }

    override fun openDraw() {
        viewModelScope.launch(Dispatchers.IO) {
            openDrawLiveData.postValue(Unit)
        }
    }

    override fun closeDraw() {
        viewModelScope.launch(Dispatchers.IO) {
            closeDrawLiveData.postValue(Unit)
        }
    }

    override fun openDialog() {
        viewModelScope.launch(Dispatchers.IO) {
            openDialogLiveData.postValue(Unit)
        }
    }

    private fun loadCategory() {
        progressLiveData.value = true
        homeUseCase.getCategoryData().onEach { result ->
            result.onSuccess { data ->
                progressLiveData.value = false
                categoryLiveData.value = data
            }
            result.onFailure { failure ->
                progressLiveData.value = false
                netWorkLiveData.value = failure.message
            }
        }.launchIn(viewModelScope)
    }

    private fun loadFood() {
        progressLiveData.value = true
        homeUseCase.getFoodsData().onEach { result ->
            result.onSuccess { data ->
                progressLiveData.value = false
                foodsLiveData.value = data
            }
            result.onFailure { failure ->
                progressLiveData.value = false
                netWorkLiveData.value = failure.message
            }
        }.launchIn(viewModelScope)
    }

    private fun loadAds() {
        progressLiveData.value = true
        homeUseCase.getAdsData().onEach { result ->
            result.onSuccess { data ->
                progressLiveData.value = false
                adsLiveData.value = data
            }
            result.onFailure { failure ->
                progressLiveData.value = false
                netWorkLiveData.value = failure.message
            }
        }.launchIn(viewModelScope)
    }
}