package com.dzenis_ska.shopinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.ActivityShopListBinding
import com.dzenis_ska.shopinglist.db.MainViewModel
import com.dzenis_ska.shopinglist.db.ShopListItemAdapter
import com.dzenis_ska.shopinglist.dialogs.EditListItemDialog
import com.dzenis_ska.shopinglist.entities.ShopListItem
import com.dzenis_ska.shopinglist.entities.ShopListNameItem

class ShopListActivity : AppCompatActivity(), ShopListItemAdapter.Listener {

    private lateinit var binding: ActivityShopListBinding
    private var shopListNameItem: ShopListNameItem? = null
    private lateinit var saveItem: MenuItem
    private  var edItem: EditText? = null
    private var adapter: ShopListItemAdapter? = null

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initRcView()
        listItemObserver()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu, menu)
        saveItem =  menu?.findItem(R.id.save_item)!!
        val newItem =  menu.findItem(R.id.new_item)!!
        edItem = newItem.actionView.findViewById(R.id.edNewShopItem) as EditText
        newItem.setOnActionExpandListener(expandActionView())
        saveItem.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_item -> adNewShopItem()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun adNewShopItem(){

        if(edItem?.text.toString().isEmpty()) return
        val item = ShopListItem(
            null,
            edItem?.text.toString(),
            "",
            false,
            shopListNameItem?.id!!,
            0
        )
        mainViewModel.insertShopItem(item)
        edItem?.text?.clear()

    }

    private fun listItemObserver(){
        mainViewModel.getAllItemsFromList(shopListNameItem?.id!!).observe(this, {
            adapter?.submitList(it)
            binding.tvEmpty.visibility = if(it.isEmpty()){
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }

    private fun initRcView() = with(binding){
        adapter = ShopListItemAdapter(this@ShopListActivity)
        rcView.layoutManager = LinearLayoutManager(this@ShopListActivity)
        rcView.adapter = adapter
    }

    private  fun expandActionView(): MenuItem.OnActionExpandListener{
        return object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                saveItem.isVisible = true
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                saveItem.isVisible = false
                invalidateOptionsMenu()
                return true
            }

        }
    }

    private fun init(){
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
    }

    companion object {
        const val SHOP_LIST_NAME = "shopListName"
    }



    override fun onClickItem(shopListItem: ShopListItem, state: Int) {
        when(state){
            ShopListItemAdapter.CHECK_BOX -> mainViewModel.updateListItem(shopListItem)
            ShopListItemAdapter.EDIT -> editListItem(shopListItem)
        }
    }
    private fun editListItem(item: ShopListItem){
        EditListItemDialog.showDialog(this, item, object : EditListItemDialog.Listener{
            override fun onClick(item: ShopListItem) {
                mainViewModel.updateListItem(item)
            }
        })
    }

}