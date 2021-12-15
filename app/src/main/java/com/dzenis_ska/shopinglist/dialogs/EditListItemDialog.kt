package com.dzenis_ska.shopinglist.dialogs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.EditListItemDialogBinding
import com.dzenis_ska.shopinglist.databinding.NewListDialogBinding
import com.dzenis_ska.shopinglist.entities.ShopListItem

object EditListItemDialog {
    fun showDialog(context: Context, item: ShopListItem, listener: Listener){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = EditListItemDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            edName.setText(item.name)
            edInfo.setText(item.itemInfo)
           bUpdate.setOnClickListener {
               if(edName.text.toString().isNotEmpty()){
                   listener.onClick(item.copy(
                       name = edName.text.toString(),
                       itemInfo = edInfo.text.toString())
                   )
               }
               dialog?.dismiss()
           }
        }

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface  Listener{
        fun onClick(item: ShopListItem)
    }
}