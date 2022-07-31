package com.kkm.geodetic.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_history")
data class HistoryTable(
    @PrimaryKey(autoGenerate = true)val id : Int,
    var date : String,

)