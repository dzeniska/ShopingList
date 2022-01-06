package com.dzenis_ska.shopinglist.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.ActivityMainBinding
import com.dzenis_ska.shopinglist.dialogs.NewListDialog
import com.dzenis_ska.shopinglist.fragments.FragmentManager
import com.dzenis_ska.shopinglist.fragments.NoteFragment
import com.dzenis_ska.shopinglist.fragments.ShopListNamesFragment
import com.dzenis_ska.shopinglist.settings.SettingsActivity

class MainActivity : AppCompatActivity(), NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding
    private lateinit var defPref: SharedPreferences
    private var currentMenuItemId = R.id.shop_list
    private var currentTheme = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        defPref = PreferenceManager.getDefaultSharedPreferences(this)
        setTheme(getSelectedTheme())
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        currentTheme = defPref.getString("theme_key", "blue").toString()

        FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
        setBottomNavListener()
    }
    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.settings ->{
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                R.id.notes ->{
                    currentMenuItemId = R.id.notes
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list ->{
                    currentMenuItemId = R.id.shop_list
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
                }
                R.id.new_item ->{

                    FragmentManager.currentFrag?.onCLickNew()
//                    NewListDialog.showDialog(this, this)
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bNav.selectedItemId = currentMenuItemId
        if(defPref.getString("theme_key", "blue").toString() != currentTheme) recreate()
    }

    private fun getSelectedTheme(): Int{
        return if(defPref.getString("theme_key", "blue") == "blue"){
            R.style.Theme_ShopingListBlue
        } else {
            R.style.Theme_ShopingListRed
        }
    }

    override fun onClick(name: String) {
        Log.d("!!!", "Name: $name")
    }
}