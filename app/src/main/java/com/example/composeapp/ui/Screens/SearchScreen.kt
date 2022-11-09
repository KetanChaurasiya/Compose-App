package com.example.composeapp.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapp.model.MagicDoor
import com.example.composeapp.ui.components.MagicDoorCard
import com.example.composeapp.viewModels.SearchScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchScreen() {
    val viewModel: SearchScreenViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    val remoteList: MutableState<List<MagicDoor>> = rememberSaveable {
        mutableStateOf(emptyList<MagicDoor>())
    }
    val isVerticalScroll: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = "SearchScreen") {
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.magicList.collect {
                remoteList.value = it
            }
        }
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(
                    modifier = Modifier
                        .height(40.dp)
                        .wrapContentSize(align = Alignment.Center),
                    onClick = {
                        viewModel.getList()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Reset")
                }
                TextButton(
                    modifier = Modifier
                        .height(40.dp)
                        .wrapContentSize(align = Alignment.Center),
                    onClick = {
                        isVerticalScroll.value = !isVerticalScroll.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Change Scroll Direction")
                }
            }
        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.Gray)
        ) {
            if (isVerticalScroll.value) {
                LazyColumn(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 5.dp, end = 5.dp, bottom = 60.dp)
                ) {
                    items(remoteList.value) { magicCard ->
                        MagicDoorCard(magicDoor = magicCard) {
                            remoteList.value = remoteList.value.map { door ->
                                if (door == magicCard) magicCard.copy(isOpened = true) else door
                            }
                        }
                    }
                }
            } else {
                LazyRow(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 5.dp, end = 5.dp, bottom = 60.dp)
                ) {
                    items(remoteList.value) { magicCard ->
                        MagicDoorCard(magicDoor = magicCard) {
                            viewModel.transformList(magicCard)
                        }
                    }
                }
            }

        }
    }


}