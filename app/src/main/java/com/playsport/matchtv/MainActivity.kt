package com.playsport.matchtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.playsport.matchtv.databinding.ActivityMainBinding
import com.playsport.matchtv.ui.BaseActivity
import com.playsport.matchtv.utils.openAct

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.buttonStart.setOnClickListener {

            openAct(BaseActivity())
        }
    }
}