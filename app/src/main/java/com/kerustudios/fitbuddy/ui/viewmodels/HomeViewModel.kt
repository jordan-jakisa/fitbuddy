package com.kerustudios.fitbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerustudios.fitbuddy.data.preferences.PreferenceKeys
import com.kerustudios.fitbuddy.data.repositories.DataStoreRepository
import com.kerustudios.fitbuddy.domain.managers.DatabaseManager
import com.kerustudios.fitbuddy.ui.dialogs.Habit
import com.kerustudios.fitbuddy.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val databaseManager: DatabaseManager
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {
        viewModelScope.launch {
            launch { initializePageEvents() }
            launch { readUserDetails() }
            launch {
                getCurrentDateHabitLog()
            }
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
                appEvents = emptyList(), userName = name
            )

        }
    }

    fun showAddWaterDialog() {
        _uiState.value = _uiState.value.copy(
            appEvents = listOf(AppEvents.ShowAddWaterDialog)
        )
    }

    fun showAddSleepDialog() {
        _uiState.value = _uiState.value.copy(
            appEvents = listOf(AppEvents.ShowAddSleepDialog)
        )
    }

    fun saveEntry(value: Float, habit: Habit) {
        viewModelScope.launch {
            databaseManager.saveEntry(value, habit).also {
                _uiState.value = _uiState.value.copy(
                    appEvents = emptyList()
                )
            }
        }
    }

    private suspend fun getCurrentDateHabitLog() {
        viewModelScope.launch {

            launch {
                databaseManager.getCurrentWaterValues(
                    date = getCurrentDate()
                ).collect {
                    _uiState.value = _uiState.value.copy(
                        totalWater = it
                    )
                }
            }

            launch {
                databaseManager.getCurrentSleepValues(
                    date = getCurrentDate(),
                ).collect {
                    _uiState.value = _uiState.value.copy(
                        totalSleep = it
                    )
                }
            }
        }
    }

}

data class HomeUiState(
    val appEvents: List<AppEvents>? = null,
    val userName: String? = null,
    val totalWater: Float? = null,
    val totalSleep: Float? = null
)

sealed class AppEvents() {
    data object IsFirstTimeUser : AppEvents()
    data object None : AppEvents()
    data object ShowAddWaterDialog : AppEvents()
    data object ShowAddSleepDialog : AppEvents()
}