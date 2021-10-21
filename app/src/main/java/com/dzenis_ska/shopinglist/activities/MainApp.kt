package com.dzenis_ska.shopinglist.activities

import android.app.Application
import com.dzenis_ska.shopinglist.db.MainDataBase

class MainApp: Application() {
    val database by lazy { MainDataBase.getDataBase(this)}
}