package com.pt2.leg5.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ulasan")
data class UlasanEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "restaurant_name")
    var restaurantName: String,

    @ColumnInfo(name = "restaurant_address")
    var restaurantAddress: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "is_clean")
    var isClean: Boolean,

    @ColumnInfo(name = "packaging")
    var packaging: Boolean,

    @ColumnInfo(name = "services")
    var services: Boolean,

    @ColumnInfo(name = "recommend")
    var recommend: Boolean

)