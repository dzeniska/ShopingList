package com.dzenis_ska.shopinglist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dzenis_ska.shopinglist.entities.NoteItem
import com.dzenis_ska.shopinglist.entities.ShopListNameItem
import kotlinx.coroutines.flow.Flow

@Dao
interface  Dao {

    @Query("SELECT * FROM note_list")
    fun getAllNotes () : Flow<List<NoteItem>>

    @Query("SELECT * FROM shopping_list_names")
    fun getAllShopListNames(): Flow<List<ShopListNameItem>>

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id: Int)

    @Query("DELETE FROM shopping_list_names WHERE id IS :id")
    suspend fun deleteShopListName(id: Int)

    @Insert
    suspend fun insertNote(note: NoteItem)

    @Insert
    suspend fun insertShopListName(note: ShopListNameItem)

    @Update
    suspend fun updateNote(note: NoteItem)

    @Update
    suspend fun updateListName(note: ShopListNameItem)

}