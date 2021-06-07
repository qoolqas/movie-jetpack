package com.raveendra.moviejetpack.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Data(
    var id: Int?,
    var name: String?,
    var desc: String?,
    var poster: String?,
    var img_preview: String?
) : Parcelable