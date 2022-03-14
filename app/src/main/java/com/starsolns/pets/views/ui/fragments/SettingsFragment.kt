package com.starsolns.pets.views.ui.fragments


import android.app.UiModeManager
import android.content.Context.UI_MODE_SERVICE
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.starsolns.pets.R


    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            setHasOptionsMenu(false)

        }

        override fun onPreferenceTreeClick(preference: Preference): Boolean {
            val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
            val enabled = pref.getBoolean("daynightmode",false)

            return if (enabled){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
        }
    }

