package com.abanapps.deepseek.r1.domain.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanapps.deepseek.r1.data.network.safeCallUtils.onError
import com.abanapps.deepseek.r1.data.network.safeCallUtils.onSuccess
import com.abanapps.deepseek.r1.domain.repo.Repo
import com.abanapps.deepseek.r1.presentation.state.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel(
    private val repo: Repo
) : ViewModel() {

    private val _chatState = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatState = _chatState.asStateFlow()

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
                _chatState.update { currentList ->
                    currentList.map { chat ->
                        if (chat.userPrompt == prompt && chat.aiResponse == null) {
                            chat.copy(aiResponse = response.message?.content, isLoading = false)
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

}