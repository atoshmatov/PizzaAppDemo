package uz.gita.pizzaappdemo.mobdev20.presentation.ui.screen.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData
import uz.gita.pizzaappdemo.mobdev20.databinding.ScreenHomeBinding
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home.HomeCategoryAdapter
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home.HomeFoodAdapter
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.HomeViewModel
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.impl.HomeViewModelImpl


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val categoryAdapter = HomeCategoryAdapter()
    private val foodAdapter = HomeFoodAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        categoryList.adapter = categoryAdapter
        foodList.adapter = foodAdapter
        categoryList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        foodList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        cardCount.text = "0"
        viewModel.categoryLiveData.observe(viewLifecycleOwner, categoryObserver)
        viewModel.foodsLiveData.observe(viewLifecycleOwner, foodsObserver)
    }


    private val foodsObserver = Observer<List<FoodData>> {
        foodAdapter.submitList(it)
    }

    private val categoryObserver = Observer<List<CategoryData>> {
        categoryAdapter.submitList(it)
    }
}