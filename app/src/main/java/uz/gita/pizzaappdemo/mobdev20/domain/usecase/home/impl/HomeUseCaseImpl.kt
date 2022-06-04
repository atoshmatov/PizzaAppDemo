package uz.gita.pizzaappdemo.mobdev20.domain.usecase.home.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData
import uz.gita.pizzaappdemo.mobdev20.domain.repository.home.HomeRepository
import uz.gita.pizzaappdemo.mobdev20.domain.usecase.home.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : HomeUseCase {
    override fun getCategoryData() = callbackFlow<Result<List<CategoryData>>> {
        withContext(Dispatchers.Default) {
            homeRepository.getCategory(
                {
                    trySendBlocking(Result.success(it))
                },
                {
                    trySendBlocking(Result.failure(it))
                }
            )
        }
        awaitClose {}
    }.flowOn(Dispatchers.IO)

    override fun getFoodsData() = callbackFlow<Result<List<FoodData>>> {
        withContext(Dispatchers.Default) {
            homeRepository.getFood(
                {
                    trySendBlocking(Result.success(it))
                },
                {
                    trySendBlocking(Result.failure(it))
                }
            )
        }
        awaitClose {}
    }.flowOn(Dispatchers.IO)

}