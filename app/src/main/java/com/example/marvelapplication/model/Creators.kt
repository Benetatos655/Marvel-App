package com.example.marvelapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
) : Parcelable