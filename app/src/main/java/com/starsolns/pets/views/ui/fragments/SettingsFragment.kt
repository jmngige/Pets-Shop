package com.starsolns.pets.views.ui.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.starsolns.pets.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        setHasOptionsMenu(false)

        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val notified = sp.getBoolean("notifications", true)

        val pref: SharedPreferences? = context?.getSharedPreferences("notify_pref", Context.MODE_PRIVATE)
        val editor = pref?.edit()

        editor?.putBoolean("notifications_id", notified)
        editor?.apply()

    }
}