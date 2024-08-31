package com.kerustudios.fitbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerustudios.fitbuddy.data.preferences.PreferenceKeys
import com.kerustudios.fitbuddy.data.repositories.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {
        viewModelScope.launch {
            launch { initializePageEvents() }
            launch { readUserDetails() }
        }
    }

    private suspend fun readUserDetails() {
        viewModelScope.launch {
            val userName = dataStoreRepository.readValue(PreferenceKeys.USER_NAME)
            if (userName != null) _uiState.value = _uiState.value.copy(userName = userName)
        }
    }

    private suspend fun initializePageEvents() {
        viewModelScope.launch {
            val userName = dataStoreRepository.readValue(PreferenceKeys.USER_NAME) ?: ""

            if (userName.isEmpty()) _uiState.value = _uiState.value.copy(
                appEvents = listOf(AppEvents.IsFirstTimeUser)
            )
        }
    }

    fun updateUserName(name: String) {
        viewModelScope.launch {
            dataStoreRepository.setValue(PreferenceKeys.USER_NAME, name)
            _uiState.value = _uiState.value.copy(
                appEvents = emptyList(),
                userName = name
            )
        }
    }

}

data class HomeUiState(
    val appEvents: List<AppEvents>? = null,
    val userName: String? = null
)

sealed class AppEvents() {
    data object IsFirstTimeUser : AppEvents()
    data object None : AppEvents()
}