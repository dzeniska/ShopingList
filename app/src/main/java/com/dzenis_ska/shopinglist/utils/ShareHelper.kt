package com.dzenis_ska.shopinglist.utils

import android.content.Intent
import com.dzenis_ska.shopinglist.entities.ShopListItem

object ShareHelper {
    fun shareShopList(shopList: List<ShopListItem>, listName: String): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plane"
        intent.apply {
            putExtra(Intent.EXTRA_TEXT, makeShareText(shopList, listName))
        }
        return  intent
    }

    private fun makeShareText(shopList: List<ShopListItem>, listName: String) : String {
        val builder = StringBuilder()
        builder.append("<<$listName>>")
        builder.append("\n")
        var counter = 0
        shopList.forEach {
            builder.append("${++counter} - ${it.name} (${it.itemInfo})")
            builder.append("\n")
        }
        return builder.toString()
    }
}