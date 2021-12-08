package com.dzenis_ska.shopinglist.dialogs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.NewListDialogBinding

object NewListDialog {
    fun showDialog(context: Context, listener: Listener, name: String){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = NewListDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            if(name.isNotEmpty()) btCreate.text = context.getString(R.string.update)
            edNewListName.setText(name)
            btCreate.setOnClickListener {
                val listName = edNewListName.text.toString()
                if(listName.isNotEmpty()){
                    listener.onClick(listName)
                }
                dialog?.dismiss()

            }
        }

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface  Listener{
        fun onClick(name: String)
    }
}