package uz.gita.pizzaappdemo.mobdev20.domain.repository.home.impl

import com.google.firebase.firestore.FirebaseFirestore
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData
import uz.gita.pizzaappdemo.mobdev20.domain.repository.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    store: FirebaseFirestore
) : HomeRepository {

    private val category = store.collection("category")
    private val foods = store.collection("foods")

    override suspend fun getCategory(
        success: (List<CategoryData>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        category.get().addOnSuccessListener { querySnapshot ->
            val response = querySnapshot.map { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(CategoryData::class.java)
            }
            success.invoke(response)
        }.addOnFailureListener {
            failure.invoke(it)
        }
    }

    override suspend fun getFood(
        success: (List<FoodData>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        foods.get().addOnSuccessListener { querySnapshot ->
            val response = querySnapshot.map { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(FoodData::class.java)
            }
            success.invoke(response)
        }.addOnFailureListener {
            failure.invoke(it)
        }
    }

}