package com.dzenis_ska.shopinglist.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dzenis_ska.shopinglist.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)

    }
}