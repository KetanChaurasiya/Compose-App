package com.example.composeapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.model.MagicDoor
import com.example.composeapp.repository.DefaultRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {
    private val _magicList = MutableStateFlow<List<MagicDoor>>(emptyList())
    val magicList: StateFlow<List<MagicDoor>> = _magicList
    private val remoteDb: DefaultRepository = DefaultRepository()

    fun getList() {
        viewModelScope.launch {
            remoteDb.getList().collect { response ->
                _magicList.value = response
            }
        }

    }
}