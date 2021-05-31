package com.khairul.bisindosignlanguangerecognition.data.source.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DictionaryEntity(
    val alphabet: String? = null,
    val image: String? = null,
    val desc: String? = null,
) : Parcelable

