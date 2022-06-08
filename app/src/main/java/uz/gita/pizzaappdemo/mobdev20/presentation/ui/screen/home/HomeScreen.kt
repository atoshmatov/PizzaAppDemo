package uz.gita.pizzaappdemo.mobdev20.presentation.ui.screen.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.pizzaappdemo.mobdev20.BuildConfig
import uz.gita.pizzaappdemo.mobdev20.MainActivity
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.data.local.home.SharedPref
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.AdsData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.CategoryData
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData
import uz.gita.pizzaappdemo.mobdev20.databinding.ScreenHomeNavBinding
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home.HomeAdsAdapter
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home.HomeCategoryAdapter
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home.HomeFoodAdapter
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.dialog.home.LangDialog
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.HomeViewModel
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.home.impl.HomeViewModelImpl
import uz.gita.pizzaappdemo.mobdev20.utils.hideKeyboard
import java.util.*


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home_nav) {
    private val binding by viewBinding(ScreenHomeNavBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private var categoryAdapter: HomeCategoryAdapter? = null
    private var foodAdapter: HomeFoodAdapter? = null
    private val adsAdapter = HomeAdsAdapter()
    private var shared: SharedPref? = null
    private var onClickEngListener1: ((Int) -> Unit)? = null
    private var onClickUzbListener2: ((Int) -> Unit)? = null
    private var onClickRuListener3: ((Int) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openDialogLiveData.observe(this@HomeScreen, openDialogObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedPref(requireContext())
        foodAdapter = HomeFoodAdapter(requireContext())
        categoryAdapter = HomeCategoryAdapter(requireContext())
        //version text
        versionAppName.text =
            resources.getString(R.string.text_splash_pizza, BuildConfig.VERSION_NAME)
        // collection adapter
        liner.categoryList.adapter = categoryAdapter
        liner.foodList.adapter = foodAdapter
        liner.adsList.adapter = adsAdapter
        liner.categoryList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        liner.foodList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        liner.adsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // author action
        liner.cardCount.text = "0"
        liner.menuBurger.setOnClickListener {
            viewModel.openDraw()
        }
        settingLiner.setOnClickListener {
            viewModel.openDialog()
        }

        //viewModel observer
        viewModel.categoryLiveData.observe(viewLifecycleOwner, categoryObserver)
        viewModel.foodsLiveData.observe(viewLifecycleOwner, foodsObserver)
        viewModel.adsLiveData.observe(viewLifecycleOwner, adsObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.openDrawLiveData.observe(viewLifecycleOwner, openDrawObserver)
        viewModel.closeDrawLiveData.observe(viewLifecycleOwner, closeDrawObserver)
    }

    // observer object
    private val openDrawObserver = Observer<Unit> {
        binding.draw.openDrawer(GravityCompat.START)
    }
    private val closeDrawObserver = Observer<Unit> {
        binding.draw.closeDrawer(GravityCompat.START)
        hideKeyboard()
    }

    private val openDialogObserver = Observer<Unit> {
        val dialog = LangDialog()
        dialog.setonClickEngListener {
            onClickEngListener1?.invoke(it)
            onOptionsItemSelected(it)
            shared?.language = 1
            viewModel.closeDraw()
        }
        dialog.setonClickUzbListener {
            onClickUzbListener2?.invoke(it)
            onOptionsItemSelected(it)
            shared?.language = 2
            viewModel.closeDraw()
        }
        dialog.setonClickRuListener {
            onClickRuListener3?.invoke(it)
            onOptionsItemSelected(it)
            shared?.language = 3
            viewModel.closeDraw()
        }
        dialog.show(childFragmentManager, "")
    }
    private val progressObserver = Observer<Boolean> {
        if (it) binding.liner.progress.show()
        else binding.liner.progress.hide()
    }
    private val adsObserver = Observer<List<AdsData>> { adsAdapter.submitList(it) }
    private val foodsObserver = Observer<List<FoodData>> { foodAdapter?.submitList(it) }
    private val categoryObserver = Observer<List<CategoryData>> { categoryAdapter?.submitList(it) }


    //author action
    private fun onOptionsItemSelected(id: Int) {
        var languageToLoad = "en";
        languageToLoad = when (id) {
            1 -> {
                "en"
            }
            2 -> {
                "uzb"
            }
            else -> {
                "ru"
            }
        }
        val locale = Locale(languageToLoad);
        Locale.setDefault(locale);
        val config = Configuration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.displayMetrics)
    }


    fun setonClickEngListener1(block: (Int) -> Unit) {
        onClickEngListener1 = block
    }

    fun setonClickUzbListener2(block: (Int) -> Unit) {
        onClickUzbListener2 = block
    }

    fun setonClickRuListener3(block: (Int) -> Unit) {
        onClickRuListener3 = block
    }
}