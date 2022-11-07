package com.example.composeapp.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

    val remotelist: MutableState<List<MagicDoor>> = remember {
        mutableStateOf(emptyList<MagicDoor>())
    }
    val isVerticalScroll: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = "SearchScreen") {
        viewModel.getList()
        coroutineScope.launch(Dispatchers.IO) {
            viewModel.magicList.collect {
                remotelist.value = it
            }
        }
    }
    Scaffold(
        topBar = {
            Row {
                TextButton(
                    modifier = Modifier
                        .height(40.dp)
                        .wrapContentSize(align = Alignment.Center),
                    //.weight(1f),
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
                    //.weight(1f),
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
                    items(remotelist.value) { magicCard ->
                        MagicDoorCard(magicDoor = magicCard) {
                            remotelist.value.find { item -> item == magicCard }?.isOpened = true
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
                    items(remotelist.value) { magicCard ->
                        MagicDoorCard(magicDoor = magicCard) {
                            remotelist.value.find { item -> item == magicCard }?.isOpened = true
                        }
                    }
                }
            }

        }
    }


}