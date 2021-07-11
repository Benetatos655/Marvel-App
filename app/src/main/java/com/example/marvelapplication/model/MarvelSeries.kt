package com.example.marvelapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class MarvelSeries(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
) : Parcelable