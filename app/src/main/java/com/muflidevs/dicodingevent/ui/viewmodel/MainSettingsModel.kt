package com.muflidevs.dicodingevent.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.muflidevs.dicodingevent.ui.settings.SettingPreferences
import kotlinx.coroutines.launch

class MainSettingsModel(private val pref: SettingPreferences) : ViewModel() {
    fun getThemeSettings() : LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSettings(isDarkModeActive : Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

}