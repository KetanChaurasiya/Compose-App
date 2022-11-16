package com.example.composeapp.contracts.searchScreen

import com.example.composeapp.model.MagicDoor

sealed class SearchScreenSideEffect {
    // for effects
    data class TransformList(val magicList: List<MagicDoor>) : SearchScreenSideEffect()

    data class ShowList(val magicList: List<MagicDoor>) : SearchScreenSideEffect()
}
