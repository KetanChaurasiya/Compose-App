package com.example.composeapp.repository

import com.example.composeapp.model.MagicDoor
import com.example.composeapp.model.RewardType
import kotlinx.coroutines.flow.flow

class DefaultRepository {


    fun getList(count: Int = 10) = generateMagicDoorList(count)
//        flow {
//        emit(generateMagicDoorList(count))
//    }


    private fun generateMagicDoorList(count: Int): List<MagicDoor> {
        val magicList = mutableListOf<MagicDoor>()
        for (number in 1..count) {
            val randomReward = RewardType.values().toList().shuffled().first()
            magicList.add(
                MagicDoor(
                    title = "Door $number",
                    rewardType = randomReward,
                    couponCode = if (randomReward == RewardType.Coupon) generateRandomCode() else null,
                    points = if (randomReward == RewardType.Points) (0..1000L).random() else null,
                    message = if (randomReward == RewardType.None) "Better Luck Next Time" else "Congratulations",
                    isOpened = false
                )
            )
        }

        return magicList
    }

    private fun generateRandomCode(): String {
        var code = ""
        for (char in 0..10) {
            val randomChar = (65..92).random()
            code = "${code}${randomChar.toChar()}".uppercase()
        }
        return code
    }

}