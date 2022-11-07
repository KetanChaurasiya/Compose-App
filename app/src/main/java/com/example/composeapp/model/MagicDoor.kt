package com.example.composeapp.model

data class MagicDoor(
    val title:String,
    val rewardType: RewardType,
    val couponCode: String?,
    val points: Long?,
    val message: String,
    var isOpened: Boolean
)
