package com.example.composeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.model.MagicDoor
import com.example.composeapp.model.RewardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicDoorCard(magicDoor: MagicDoor?, isOpened: (Boolean) -> Unit) {
    magicDoor?.let {
        var dialogState by remember {
            mutableStateOf(false)
        }
        CustomDialogCallState(
            magicDoor = magicDoor,
            dialogState = dialogState,
            onDismissRequest = { dialogState = !it })
        Card(
            modifier = Modifier
                .width(200.dp)
                .padding(10.dp)
                .height(150.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            onClick = {
                dialogState = true
                isOpened(true)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    if (magicDoor.isOpened) {
                        OpenedDoor(magicDoor)
                    } else {
                        ClosedDoor()
                    }
                }
            }
        }
    }
}

@Composable
fun OpenedDoor(magicDoor: MagicDoor) {
    Text(
        text = if (magicDoor.rewardType != RewardType.None) "Won" else "Lost",
        fontWeight = FontWeight.Bold,
        color = if (magicDoor.rewardType != RewardType.None) Color.Green else Color.Red,
        modifier = Modifier
            .padding(start = 2.dp, top = 2.dp),
        style = MaterialTheme.typography.displaySmall
    )
    Text(
        text = magicDoor.title, fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 2.dp, top = 2.dp),
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
fun ClosedDoor() {
    Icon(
        Icons.Default.Search,
        "?",
        modifier = Modifier
            .size(45.dp),
        tint = Color.Red
    )
    Text(
        text = "?", fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 2.dp, top = 2.dp),
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
@Preview
fun previewDisplayCard() {
    MagicDoorCard(magicDoor = MagicDoor("Test", RewardType.None, null, null, "Test", false)) {

    }
}