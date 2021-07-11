package com.example.marvelapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Previous(
    val name: String,
    val resourceURI: String
) : Parcelable