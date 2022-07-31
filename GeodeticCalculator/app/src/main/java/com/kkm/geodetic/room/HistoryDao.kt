package com.kkm.geodetic.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HistoryDao {
    @Query("SELECT * FROM tb_history")
    fun getAll(): List<HistoryTable>

    @Insert
    fun insertAll(vararg destRoom: HistoryTable)

    @Delete
    fun delete(destRoom: HistoryTable)

}