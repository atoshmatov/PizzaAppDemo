package uz.gita.pizzaappdemo.mobdev20

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.pizzaappdemo.mobdev20.presentation.ui.screen.home.HomeScreen
import uz.gita.pizzaappdemo.mobdev20.utils.LangHelper
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var langHelper: LangHelper? = null
    private var homeScreen: HomeScreen? = null
    private var count = 0
    private var onBack: ((Int) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        langHelper = LangHelper(this)
        homeScreen = HomeScreen()

        homeScreen?.setonClickEngListener1 {
            langHelper!!.onOptionsItemSelected(it)
            onOptionsItemSelected(it)
        }
        homeScreen?.setonClickUzbListener2 {
            langHelper!!.onOptionsItemSelected(it)
            onOptionsItemSelected(it)
        }
        homeScreen?.setonClickRuListener3 {
            langHelper!!.onOptionsItemSelected(it)
            onOptionsItemSelected(it)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
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
        this.resources.updateConfiguration(config, this.resources.displayMetrics)
        recreate()
        onRestart()
        /*val config = resources.configuration
        val lang = "fa" // your language code
        val locale = Locale(lang)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)*/
    }
}