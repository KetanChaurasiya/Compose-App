package com.example.composeapp.contracts.searchScreen

import android.os.Parcel
import android.os.Parcelable
import com.example.composeapp.model.MagicDoor


data class SearchScreenState(
    var magicList: List<MagicDoor> = emptyList()
): Parcelable {
    constructor(parcel: Parcel) : this(emptyList()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchScreenState> {
        override fun createFromParcel(parcel: Parcel): SearchScreenState {
            return SearchScreenState(parcel)
        }

        override fun newArray(size: Int): Array<SearchScreenState?> {
            return arrayOfNulls(size)
        }
    }
}
