package uz.gita.pizzaappdemo.mobdev20.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.pizzaappdemo.mobdev20.BuildConfig

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
            if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}