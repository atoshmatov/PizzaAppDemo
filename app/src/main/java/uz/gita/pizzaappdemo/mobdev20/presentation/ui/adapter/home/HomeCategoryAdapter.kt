package uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.data.local.home.SharedPref
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.databinding.CategoryItemBinding

class HomeCategoryAdapter(val context: Context) :
    ListAdapter<CategoryData, HomeCategoryAdapter.ViewHolder>(HomeCategoryDiffUtils) {
    lateinit var sharedPref: SharedPref

    object HomeCategoryDiffUtils : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

    }

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        @SuppressLint("ResourceAsColor")
        fun bind(): CategoryData = with(binding) {
            sharedPref = SharedPref(context)
            getItem(absoluteAdapterPosition).apply {
                when (sharedPref.language) {
                    1 -> {
                        categoryName.text = name_eng
                    }
                    2 -> {
                        categoryName.text = name_uz
                    }
                    else -> {
                        categoryName.text = name_ru
                    }
                }
                if (selected!!) {
                    Glide
                        .with(categoryImage)
                        .load(categoryImageUrl)
                        .into(categoryImage)
                } else {
                    Glide
                        .with(categoryImage)
                        .load(categoryImageUrl2)
                        .into(categoryImage)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CategoryItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}