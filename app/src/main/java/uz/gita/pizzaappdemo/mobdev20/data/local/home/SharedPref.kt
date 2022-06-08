package uz.gita.pizzaappdemo.mobdev20.data.local.home

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) {
    private val pref = context.getSharedPreferences("Contact", Context.MODE_PRIVATE)

    var language: Int
        get() = pref.getInt("LANG", 0)
        set(value) = pref.edit().putInt("LANG", value).apply()
}
