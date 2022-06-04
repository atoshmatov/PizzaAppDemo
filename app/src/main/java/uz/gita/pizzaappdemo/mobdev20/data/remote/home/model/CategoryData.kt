package uz.gita.pizzaappdemo.mobdev20.data.remote.home.model

import java.io.Serializable

data class CategoryData(
    val id: Int? = 0,
    val name_uz: String? = "",
    val name_ru: String? = "",
    val name_eng: String? = "",
    val categoryImageUrl: String? = "",
    val isSelected: Boolean? = false
) : Serializable
