package uz.gita.pizzaappdemo.mobdev20.utils

import android.content.Context
import android.content.res.Configuration
import java.util.*

class LangHelper(val context: Context) {
    fun onOptionsItemSelected(id: Int) {
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
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}