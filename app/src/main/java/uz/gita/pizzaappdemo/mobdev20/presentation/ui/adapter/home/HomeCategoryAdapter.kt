package uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.databinding.CategoryItemBinding

class HomeCategoryAdapter :
    ListAdapter<CategoryData, HomeCategoryAdapter.ViewHolder>(HomeCategoryDiffUtils) {
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

        fun bind(): CategoryData = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                categoryName.text = name_eng
                Glide
                    .with(categoryImage)
                    .load(categoryImageUrl)
                    .into(categoryImage)
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