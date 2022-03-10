package com.starsolns.pets.views.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.starsolns.pets.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        setHasOptionsMenu(false)
    }
}