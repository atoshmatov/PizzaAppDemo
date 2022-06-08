package uz.gita.pizzaappdemo.mobdev20.presentation.ui.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.pizzaappdemo.mobdev20.BuildConfig
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.databinding.ScreenSplashBinding
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.splash.SplashViewModel
import uz.gita.pizzaappdemo.mobdev20.presentation.viewmodel.splash.impl.SplashViewModelImpl

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.openNextHomeScreenLiveData.observe(this@SplashScreen, openNextHomeScreenObserver)
        appVersion.text = resources.getString(R.string.text_splash_pizza, BuildConfig.VERSION_NAME)
    }


    private val openNextHomeScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }
}