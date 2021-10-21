package com.dzenis_ska.shopinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavListener()
    }
    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.settings ->{
                    Log.d("!!!", "settings")
                }
                R.id.notes ->{Log.d("!!!", "notes")}
                R.id.shop_list ->{Log.d("!!!", "shop_list")}
                R.id.new_item ->{Log.d("!!!", "new_item")}
            }
            true
        }
    }
}