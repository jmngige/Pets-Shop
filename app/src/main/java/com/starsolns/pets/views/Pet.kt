package com.starsolns.pets.views

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pet(
    val id: Int,
    val name: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    val origin: String?,
    @SerializedName("temperament")
    val description: String?,
    @SerializedName("url")
    val imageUrl: String?
): Serializable