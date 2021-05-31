package com.khairul.bisindosignlanguangerecognition.data.source.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberEntity (
    val number: String? = null,
    val image: String? = null,
    val desc: String? = null,
) : Parcelable