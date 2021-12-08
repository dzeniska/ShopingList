package com.dzenis_ska.shopinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dzenis_ska.shopinglist.databinding.ActivityShopListBinding
import com.dzenis_ska.shopinglist.db.MainViewModel
import com.dzenis_ska.shopinglist.entities.ShopListNameItem

class ShopListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopListBinding
    private var shopListNameItem: ShopListNameItem? = null

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() = with(binding){
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
        textView2.text = shopListNameItem!!.name.toString()

    }

    companion object {
        const val SHOP_LIST_NAME = "shopListName"
    }

}