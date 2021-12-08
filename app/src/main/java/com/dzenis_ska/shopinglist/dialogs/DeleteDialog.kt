package com.dzenis_ska.shopinglist.dialogs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.dzenis_ska.shopinglist.R
import com.dzenis_ska.shopinglist.databinding.DeleteDialogBinding
import com.dzenis_ska.shopinglist.databinding.NewListDialogBinding

object DeleteDialog {
    fun showDialog(context: Context, listener: Listener){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DeleteDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            bDelete.setOnClickListener {
                listener.onClick()
                dialog?.dismiss()
            }
            bCancel.setOnClickListener {
                dialog?.dismiss()
            }
        }

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface  Listener{
        fun onClick()
    }
}