package com.dzenis_ska.shopinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.ActivityMainBinding
import com.dzenis_ska.shopinglist.dialogs.NewListDialog
import com.dzenis_ska.shopinglist.fragments.FragmentManager
import com.dzenis_ska.shopinglist.fragments.NoteFragment
import com.dzenis_ska.shopinglist.fragments.ShopListNamesFragment

class MainActivity : AppCompatActivity(), NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
        setBottomNavListener()
    }
    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.settings ->{
                    Log.d("!!!", "settings")
                }
                R.id.notes ->{
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list ->{
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
                }
                R.id.new_item ->{
                    //todo hrenj
                    FragmentManager.currentFrag?.onCLickNew()
//                    NewListDialog.showDialog(this, this)
                }
            }
            true
        }
    }

    override fun onClick(name: String) {
        Log.d("!!!", "Name: $name")
    }
}