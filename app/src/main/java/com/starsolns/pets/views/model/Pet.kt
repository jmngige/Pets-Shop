package com.starsolns.pets.views.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "pets_table")
data class Pet(
    val id: Int,
    val name: String?,
    @SerializedName("bred_for")
    @ColumnInfo(name = "bred_for")
    val bredFor: String?,
    @SerializedName("breed_group")
    @ColumnInfo(name = "breed_group")
    val breedGroup: String?,
    val origin: String?,
    @SerializedName("temperament")
    @ColumnInfo(name = "temperament")
    val description: String?,
    @SerializedName("url")
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "uuid")
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
): Serializable