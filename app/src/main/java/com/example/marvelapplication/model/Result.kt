package com.example.marvelapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val characters: Characters? = null,
    val comics: Comics? = null,
    val creators: Creators? = null,
    val description: String? = null,
    val endYear: Int? = null,
    val events: Events? = null,
    val id: Int? = null,
    val modified: String? = null,
    val next: Next? = null,
    val previous: Previous? = null,
    val rating: String? = null,
    val resourceURI: String? = null,
    val startYear: Int? = null,
    val stories: Stories? = null,
    val thumbnail: Thumbnail? = null,
    val title: String? = null,
    val type: String? = null,
    val urls: List<Url>? = null
) : Parcelable