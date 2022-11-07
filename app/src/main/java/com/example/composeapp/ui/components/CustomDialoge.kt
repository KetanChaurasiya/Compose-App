package com.example.composeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.composeapp.model.MagicDoor
import com.example.composeapp.model.RewardType

@Composable
fun CustomDialogCallState(
    magicDoor: MagicDoor,
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit
) {
    if (dialogState) {
        AlertDialog(
            containerColor = Color.DarkGray,
            onDismissRequest = { onDismissRequest(dialogState) },
            title = null,
            text = null,
            confirmButton = {
                DialogContent(magicDoor, dialogState) {
                    onDismissRequest(it)
                }
            },
            dismissButton = { onDismissRequest(false) },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun DialogContent(
    magicDoor: MagicDoor,
    dialogState: Boolean,
    onDismiss: (dialogState: Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = magicDoor.message,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = magicDoor.title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        if (magicDoor.rewardType != RewardType.None) {
            Row {

                Text(
                    text = "${magicDoor.rewardType} : ",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                Text(
                    text = if (magicDoor.rewardType == RewardType.Coupon) magicDoor.couponCode!! else magicDoor.points!!.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
        }
        Divider(
            color = Color.White, thickness = 0.8.dp,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f),
                onClick = {
                    onDismiss(dialogState)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Okay")
            }
        }
    }

}

@Composable
@Preview
fun PreviewDisplayCard() {
    DialogContent(
        magicDoor = MagicDoor("Test", RewardType.None, "hasajs", null, "Test",true), true
    ) {
    }
}