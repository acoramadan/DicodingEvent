package com.muflidevs.dicodingevent.ui.settings

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.databinding.ActivitySettingsBinding
import com.muflidevs.dicodingevent.ui.viewmodel.MainSettingsModel
import com.muflidevs.dicodingevent.ui.viewmodel.ViewModelSettingsFactory

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelSettingsFactory(pref)).get(
            MainSettingsModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive : Boolean ->
            if(isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true

            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false

            }
        }
        binding.switchTheme.setOnCheckedChangeListener{_: CompoundButton?, isChecked : Boolean ->
            mainViewModel.saveThemeSettings(isChecked)
        }
        binding.back.setOnClickListener{
            finish()
        }

    }
}