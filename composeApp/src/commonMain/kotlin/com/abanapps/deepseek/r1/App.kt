package com.abanapps.deepseek.r1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abanapps.deepseek.r1.data.utils.Utils
import com.abanapps.deepseek.r1.domain.viewModel.MainViewModel
import com.abanapps.deepseek.r1.presentation.colors.bgColor
import com.abanapps.deepseek.r1.presentation.state.ChatMessage
import com.abanapps.deepseek.r1.presentation.utils.DarkModeSwitch
import com.abanapps.deepseek.r1.presentation.utils.customFont
import deepseekr1.composeapp.generated.resources.Res
import deepseekr1.composeapp.generated.resources.ai_title
import deepseekr1.composeapp.generated.resources.ic_chat
import deepseekr1.composeapp.generated.resources.ic_message
import deepseekr1.composeapp.generated.resources.ic_panel
import deepseekr1.composeapp.generated.resources.ic_robot
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(viewModel: MainViewModel = koinViewModel()) {


    MaterialTheme {

        Column {

            HomeScreen(viewModel = viewModel)

        }


    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {

    var prompt by remember {
        mutableStateOf("")
    }

    var showModelsDropDown by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    val chatState by viewModel.chatState.collectAsState()

    var isLoading by remember {
        mutableStateOf(false)
    }

    var selectedAiModel by remember {
        mutableStateOf(Utils.models[0].first)
    }

    var mode by remember {
        mutableStateOf(false)
    }

    var showChatHistoryColumn by remember {
        mutableStateOf(false)
    }

    val modelToName = Utils.models.find {
        selectedAiModel == it.first
    }?.second


    Scaffold(
        containerColor = bgColor
    ) { innerPadding ->

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            if (showChatHistoryColumn) {

                Column(
                    modifier = Modifier.fillMaxHeight().weight(.3f).background(Color(0xFFFFFDFD))
                ) {

                    Column(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = {
                                scope.launch {
                                    showChatHistoryColumn = false
                                }
                            }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_panel),
                                    contentDescription = "ic_panel"
                                )
                            }

                            IconButton(onClick = {
                                scope.launch {
                                    showChatHistoryColumn = false
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "ic_panel"
                                )
                            }
                        }

                        repeat(8) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    "Write a programme in C++",
                                    color = Color.Black,
                                    fontFamily = customFont()
                                )
                                Icon(
                                    imageVector = Icons.Filled.MoreVert,
                                    contentDescription = "more"
                                )
                            }
                        }
                    }


                }

            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 14.dp, vertical = 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {

                        if (!showChatHistoryColumn) {
                            IconButton(onClick = {
                                scope.launch {
                                    showChatHistoryColumn = true
                                }
                            })
                            {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_panel),
                                    contentDescription = "ic_panel"
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Text(
                                modelToName!!,
                                color = Color.Gray,
                                fontFamily = customFont(),
                                fontSize = 17.sp
                            )
                            IconButton(onClick = {
                                showModelsDropDown = !showModelsDropDown
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "ic_arrow_down"
                                )
                            }

                            if (showModelsDropDown) {
                                DropdownMenu(expanded = showModelsDropDown, onDismissRequest = {
                                    showModelsDropDown = false
                                }, containerColor = Color.White) {
                                    Utils.models.forEach {
                                        DropdownMenuItem(text = {
                                            Text(it.second, fontFamily = customFont())
                                        }, onClick = {
                                            selectedAiModel = it.first
                                            showModelsDropDown = false
                                        }, leadingIcon = {
                                            Icon(
                                                painter = painterResource(resource = Res.drawable.ic_chat),
                                                contentDescription = "ic_chat",
                                                modifier = Modifier.size(24.dp)
                                            )
                                        })

                                    }
                                }
                            }
                        }
                    }



                    DarkModeSwitch(
                        modifier = Modifier,
                        checked = mode,
                        onCheckedChanged = {
                            mode = it
                        })


                }

                Image(
                    painter = painterResource(resource = Res.drawable.ic_robot),
                    contentDescription = "ic_robot",
                    modifier = Modifier
                        .size(180.dp)
                )

                Text(
                    org.jetbrains.compose.resources.stringResource(Res.string.ai_title),
                    color = Color.Black,
                    fontFamily = customFont(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )



                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {

                    items(chatState) {

                        isLoading = it.isLoading

                        ChatBubble(chat = it)

                    }

                }


                OutlinedTextField(
                    value = prompt,
                    onValueChange = {
                        prompt = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(resource = Res.drawable.ic_message),
                            contentDescription = "ic_message",
                            tint = Color.Black
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.generateAiResponse(prompt, model = selectedAiModel)
                            prompt = ""
                        })
                        {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "ic_snd",
                                tint = Color.Black
                            )
                        }
                    }
                )


            }


        }


    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChatBubble(chat: ChatMessage) {

    Column(modifier = Modifier.fillMaxWidth()) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = chat.userPrompt,
                modifier = Modifier
                    .background(
                        Color.LightGray.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                color = Color.Black,
                fontFamily = customFont(),
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if (chat.isLoading) {

                CircularProgressIndicator()

            } else {
                FlowRow(
                    modifier = Modifier
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    SelectionContainer {
                        Text(
                            text = chat.aiResponse ?: "Error: No response",
                            color = Color.Black,
                            fontFamily = customFont(),
                        )
                    }

                }

            }
        }

    }

}



