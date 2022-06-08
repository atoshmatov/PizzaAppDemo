package uz.gita.pizzaappdemo.mobdev20.data.remote.home.model

import java.io.Serializable

data class FoodData(
    val id: Int? = 0,
    val name_uz: String? = "",
    val name_ru: String? = "",
    val name_eng: String? = "",
    val description_uz: String? = "",
    val description_ru: String? = "",
    val description_eng: String? = "",
    val event_uz: String? = "",
    val event_ru: String? = "",
    val event_eng: String? = "",
    val foodPrice_uz: String? = "",
    val foodPrice_ru: String? = "",
    val foodPrice_eng: String? = "",
    val foodImageUrl: String? = "",
    val categoryID: Int? = 0
):Serializable
