package uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.AdsData
import uz.gita.pizzaappdemo.mobdev20.databinding.AdsItemBinding

class HomeAdsAdapter :
    ListAdapter<AdsData, HomeAdsAdapter.ViewHolder>(HomeAdsDiffUtils) {
    object HomeAdsDiffUtils : DiffUtil.ItemCallback<AdsData>() {
        override fun areItemsTheSame(oldItem: AdsData, newItem: AdsData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AdsData, newItem: AdsData): Boolean =
            oldItem == newItem

    }

    inner class ViewHolder(private val binding: AdsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(): AdsData = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                Glide
                    .with(adsImage)
                    .load(adsImageUrl)
                    .into(adsImage)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AdsItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.ads_item, parent, false)
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}