package com.abanapps.deepseek.r1.domain.viewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanapps.deepseek.r1.data.local.room.entity.ChatEntity
import com.abanapps.deepseek.r1.data.network.safeCallUtils.onError
import com.abanapps.deepseek.r1.data.network.safeCallUtils.onSuccess
import com.abanapps.deepseek.r1.data.repo.RepoImpl
import com.abanapps.deepseek.r1.presentation.state.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


class MainViewModel(
    private val repo: RepoImpl,
    private val prefs: DataStore<Preferences>
) : ViewModel() {

    private val _chatState = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatState = _chatState.asStateFlow()

    private val _themePreference = MutableStateFlow(false)
    val themePreference = _themePreference.asStateFlow()

    private val _chatsList = MutableStateFlow<List<ChatEntity>?>(emptyList())
    val chatList = _chatsList.asStateFlow()

    private val _loadAllChats = MutableStateFlow(false)
    val loadAllChats = _loadAllChats.onStart {
        getAllChats()
    }.stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(5000L), _chatsList.value)

    init {
        viewModelScope.launch {
            prefs.data
                .map { preferences ->
                    val darkThemeKey = booleanPreferencesKey("theme_preference")
                    preferences[darkThemeKey] ?: false
                }
                .collect { theme ->
                    _themePreference.value = theme
                }
        }
    }


    fun generateAiResponse(
        prompt: String,
        model: String
    ) {



        _chatState.update { currentList ->
            currentList + ChatMessage(isLoading = true, userPrompt = prompt, aiResponse = null)
        }

        viewModelScope.launch {
            repo.generateAiResponse(
                prompt = prompt,
                model = model
            ).onSuccess { response ->

                repo.addChat(
                    ChatEntity(
                        chat = ChatMessage(
                            userPrompt = prompt,
                            aiResponse = response.message?.content
                        )
                    )
                )

                _chatState.update { currentList ->

                    currentList.map { chat ->
                        if (chat.userPrompt == prompt && chat.aiResponse == null) {
                            chat.copy(
                                aiResponse = response.message?.content,
                                isLoading = false,
                                currentTime = Clock.System.now().epochSeconds
                            )
                        } else chat
                    }

                }


            }.onError { error ->
                _chatState.update { currentList ->
                    currentList.map { chat ->
                        if (chat.userPrompt == prompt && chat.aiResponse == null) {
                            chat.copy(aiResponse = "Error: ${error.name}", isLoading = false)
                        } else chat
                    }
                }
            }
        }

    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newState = !_themePreference.value
            prefs.edit { dataStore ->
                val darkThemeKey = booleanPreferencesKey("theme_preference")
                dataStore[darkThemeKey] = newState
            }
        }
    }

    private fun getAllChats() {


        viewModelScope.launch {
            repo.getChats().collectLatest {
                _chatsList.value = it

            }
        }


    }


}