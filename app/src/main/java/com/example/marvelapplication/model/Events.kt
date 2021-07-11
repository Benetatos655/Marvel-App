package com.example.marvelapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
) : Parcelable