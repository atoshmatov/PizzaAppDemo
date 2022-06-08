package uz.gita.pizzaappdemo.mobdev20.presentation.ui.dialog.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.databinding.LanguageItemBinding

class LangDialog : DialogFragment(R.layout.language_item) {

    private val binding by viewBinding(LanguageItemBinding::bind)
    private var onClickEngListener: ((Int) -> Unit)? = null
    private var onClickUzbListener: ((Int) -> Unit)? = null
    private var onClickRuListener: ((Int) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        view.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        eng.setOnClickListener {
            onClickEngListener?.invoke(1)
            dismiss()
        }
        uzb.setOnClickListener {
            onClickUzbListener?.invoke(2)
            dismiss()
        }
        ru.setOnClickListener {
            onClickRuListener?.invoke(3)
            dismiss()
        }
    }

    fun setonClickEngListener(block: (Int) -> Unit) {
        onClickEngListener = block
    }

    fun setonClickUzbListener(block: (Int) -> Unit) {
        onClickUzbListener = block
    }

    fun setonClickRuListener(block: (Int) -> Unit) {
        onClickRuListener = block
    }
}