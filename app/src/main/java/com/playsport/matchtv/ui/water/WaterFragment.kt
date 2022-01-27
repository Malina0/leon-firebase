package com.playsport.matchtv.ui.water

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.edit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.playsport.matchtv.R
import com.playsport.matchtv.databinding.FragmentWaterBinding

class WaterFragment : Fragment(R.layout.fragment_water) {

    private val binding: FragmentWaterBinding by viewBinding()

    private var liter = 0f
    private lateinit var sp: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sp = requireActivity().getSharedPreferences("SP", Context.MODE_PRIVATE)
        var liter = sp.getFloat("liter", 0f)
        with(binding) {
            textViewLitrs.text = "$liter л."


            buttonPlus.setOnClickListener {
                liter += 0.5f
                sp.edit { putFloat("liter", liter) }
                update()
            }

            buttonMines.setOnClickListener {
                if (liter != 0f) {
                    liter -= 0.5f
                    sp.edit { putFloat("liter", liter) }
                    update()
                }
            }
        }

    }

    private fun update() {
        liter = sp.getFloat("liter", 0f)
        binding.textViewLitrs.text = "$liter л."
    }
}