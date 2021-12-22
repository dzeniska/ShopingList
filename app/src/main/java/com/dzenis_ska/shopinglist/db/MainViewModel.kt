package com.dzenis_ska.shopinglist.db

import android.util.Log
import androidx.lifecycle.*
import com.dzenis_ska.shopinglist.entities.LibraryItem
import com.dzenis_ska.shopinglist.entities.NoteItem
import com.dzenis_ska.shopinglist.entities.ShopListItem
import com.dzenis_ska.shopinglist.entities.ShopListNameItem
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(dataBase: MainDataBase) : ViewModel() {
    val dao = dataBase.getDao()

    val libraryItems = MutableLiveData<List<LibraryItem>>()

    val allNotes : LiveData<List<NoteItem>> = dao.getAllNotes().asLiveData()

    val allShopListNamesItem : LiveData<List<ShopListNameItem>> = dao.getAllShopListNames().asLiveData()

    fun getAllItemsFromList(listId: Int): LiveData<List<ShopListItem>>{
        return dao.getAllShopListItems(listId).asLiveData()
    }

    fun getAllLibraryItems(name: String) = viewModelScope.launch {
        libraryItems.value = dao.getAllLibraryItems(name)
        }

    fun insertNote(note: NoteItem) {
        viewModelScope.launch {
            dao.insertNote(note)
        }
    }
    fun insertShopListName(listNameItem: ShopListNameItem) = viewModelScope.launch {
        dao.insertShopListName(listNameItem)
    }
    fun insertShopItem(shopListItem: ShopListItem) = viewModelScope.launch {
//        Log.d("!!!", "${shopListItem}")
        dao.insertItem(shopListItem)
        if(!isLibraryItemExists(shopListItem.name)) dao.insertLibraryItem(LibraryItem(null, shopListItem.name))
    }

    fun updateNote(note: NoteItem) = viewModelScope.launch {
        dao.updateNote(note)
    }

    fun updateLibraryItem(item: LibraryItem) = viewModelScope.launch {
        dao.updateLibraryItem(item)
    }

    fun updateListItem(note: ShopListItem) = viewModelScope.launch {
        dao.updateListItem(note)
    }

    fun updateListName(shopListNameItem: ShopListNameItem) = viewModelScope.launch {
        dao.updateListName(shopListNameItem)
    }

    fun deleteNote(id: Int) = viewModelScope.launch {
            dao.deleteNote(id)
        }

    fun deleteLibraryItem(id: Int) = viewModelScope.launch {
        dao.deleteLibraryItem(id)
    }

    fun deleteShopList(id: Int, deleteList: Boolean) = viewModelScope.launch {
        if(deleteList) dao.deleteShopListName(id)
        dao.deleteShopItemsById(id)
    }

    private suspend fun isLibraryItemExists(name: String): Boolean {
        return dao.getAllLibraryItems(name).isNotEmpty()
    }


    class MainViewModelFactory(val dataBase: MainDataBase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST")
                    return MainViewModel(dataBase) as T
                }
            throw IllegalArgumentException("Uncnown ViewModelClass")
        }

    }
}