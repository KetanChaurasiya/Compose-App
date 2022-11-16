package com.example.composeapp.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.composeapp.contracts.searchScreen.SearchScreenSideEffect
import com.example.composeapp.contracts.searchScreen.SearchScreenState
import com.example.composeapp.model.MagicDoor
import com.example.composeapp.repository.DefaultRepository
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SearchScreenViewModel(
    savedStateHandle: SavedStateHandle
) : ContainerHost<SearchScreenState, SearchScreenSideEffect>, ViewModel() {
    //    private val _magicList = MutableStateFlow<List<MagicDoor>>(emptyList())
//    val magicList: StateFlow<List<MagicDoor>> = _magicList
    private val remoteDb: DefaultRepository = DefaultRepository()

    // setting up mvi container
    // yet to explore excepton handling
    override val container = container<SearchScreenState, SearchScreenSideEffect>(
        initialState = SearchScreenState(),
        savedStateHandle = savedStateHandle,
        buildSettings = { this.exceptionHandler = exceptionHandler }
    ) {
        SearchScreenState()
    }

    init {
        loadMagicList()
    }

    private fun loadMagicList() {
        intent {
            val list = remoteDb.getList()

            reduce {
                state.copy(magicList = list)
            }
            postSideEffect(SearchScreenSideEffect.ShowList(list))
        }
    }


    fun resetList() {
        loadMagicList()
    }

    fun transformList(magicDoor: MagicDoor) {
        intent {
            val updatedList = state.magicList.map { door ->
                if (door == magicDoor) magicDoor.copy(
                    isOpened = true
                ) else door
            }
            reduce {
                state.copy(magicList = updatedList)
            }
            postSideEffect(
                SearchScreenSideEffect.TransformList(updatedList)
            )
        }
    }
}