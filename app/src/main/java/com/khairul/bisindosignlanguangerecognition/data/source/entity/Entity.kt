package com.khairul.bisindosignlanguangerecognition.data.source.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Entity : Parcelable {

    var label: String? = null
    var keterangan: String? = null
    var gambar: String? = null

    constructor()
    private constructor(parcel: Parcel) {
        label = parcel.readString()
        keterangan = parcel.readString()
        gambar = parcel.readString()

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(label)
        dest?.writeString(keterangan)
        dest?.writeString(gambar)

    }

    companion object CREATOR : Parcelable.Creator<Entity> {
        override fun createFromParcel(parcel: Parcel): Entity {
            return Entity(parcel)
        }

        override fun newArray(size: Int): Array<Entity?> {
            return arrayOfNulls(size)
        }
    }
}