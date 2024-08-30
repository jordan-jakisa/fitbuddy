package com.kerustudios.fitbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerustudios.fitbuddy.data.preferences.PreferenceKeys
import com.kerustudios.fitbuddy.domain.DataStoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepositoryImpl: DataStoreRepositoryImpl
) : ViewModel() {

    private var _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        viewModelScope.launch {
            launch { initializePageEvents() }
        }
    }

    private suspend fun initializePageEvents() {
        viewModelScope.launch {
            val isFirstTimeUser =
                dataStoreRepositoryImpl.readValue(PreferenceKeys.IS_FIRST_TIME_USER)

            if (isFirstTimeUser == null) _uiState.value = _uiState.value.copy(
                appEvents = listOf(AppEvents.IsFirstTimeUser)
            )
        }
    }

}

data class UiState(
    val appEvents: List<AppEvents>? = null
)

sealed class AppEvents() {
    data object IsFirstTimeUser : AppEvents()
    data object None : AppEvents()
}