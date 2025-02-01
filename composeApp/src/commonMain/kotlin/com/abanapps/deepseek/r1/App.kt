package com.abanapps.deepseek.r1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.abanapps.deepseek.r1.presentation.colors.bgColorDark
import com.abanapps.deepseek.r1.presentation.colors.bgColorLight
import com.abanapps.deepseek.r1.presentation.colors.darkChatCardColor
import com.abanapps.deepseek.r1.presentation.colors.darkChatCardUser
import com.abanapps.deepseek.r1.presentation.colors.drawerColorDark
import com.abanapps.deepseek.r1.presentation.colors.drawerColorLight
import com.abanapps.deepseek.r1.presentation.colors.iconsDarkColor
import com.abanapps.deepseek.r1.presentation.colors.iconsLightColor
import com.abanapps.deepseek.r1.presentation.state.ChatMessage
import com.abanapps.deepseek.r1.presentation.utils.DarkModeSwitch
import com.abanapps.deepseek.r1.presentation.utils.customFont
import deepseekr1.composeapp.generated.resources.Res
import deepseekr1.composeapp.generated.resources.ai_title
import deepseekr1.composeapp.generated.resources.depeseek_logo
import deepseekr1.composeapp.generated.resources.ic_chat
import deepseekr1.composeapp.generated.resources.ic_message
import deepseekr1.composeapp.generated.resources.ic_panel
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

    val darkTheme by viewModel.themePreference.collectAsState()

    val backgroundColor = (if (darkTheme) bgColorDark else bgColorLight)


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

    var showChatHistoryColumn by remember {
        mutableStateOf(false)
    }

    val modelToName = Utils.models.find {
        selectedAiModel == it.first
    }?.second


    Scaffold(
        containerColor = animateColorAsState(
            targetValue = backgroundColor,
            animationSpec = tween(1000),
            label = "backgroundColor"
        ).value
    ) { innerPadding ->

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            AnimatedVisibility(
                visible = showChatHistoryColumn,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f)
                    .background(if (darkTheme) drawerColorDark else drawerColorLight),
                enter = slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(500)),

                exit = slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(300))
            ) {
                Column {
                    Column(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = {
                                showChatHistoryColumn = false
                            }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_panel),
                                    contentDescription = "ic_panel",
                                    tint = if (darkTheme) iconsDarkColor else iconsLightColor
                                )
                            }

                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "ic_panel",
                                    tint = if (darkTheme) iconsDarkColor else iconsLightColor
                                )
                            }
                        }

                        Image(
                            painter = painterResource(resource = Res.drawable.depeseek_logo),
                            contentDescription = "deepseek_logo",
                            modifier = Modifier
                                .size(180.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        repeat(8) {
                            Card(
                                onClick = {},
                                colors = CardDefaults.cardColors(containerColor = if (darkTheme) darkChatCardColor else Color.White)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "Write a programme in C++",
                                        fontFamily = customFont(),
                                        modifier = Modifier.padding(6.dp),
                                        color = if (darkTheme) Color.White else Color.Black,
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = "more",
                                        modifier = Modifier.padding(6.dp),
                                        tint = if (darkTheme) Color.White else Color.Black,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }


//            AnimatedVisibility(
//                visible = showChatHistoryColumn,
//                modifier = Modifier.fillMaxHeight().weight(.3f).background(Color(0xFFFFFDFD))
//            ) {
//
//                Column(
//                    modifier = Modifier
//                ) {
//
//                    Column(
//                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//
//                        Spacer(modifier = Modifier.height(20.dp))
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            IconButton(onClick = {
//                                showChatHistoryColumn = false
//                            }) {
//                                Icon(
//                                    painter = painterResource(Res.drawable.ic_panel),
//                                    contentDescription = "ic_panel"
//                                )
//                            }
//
//                            IconButton(onClick = {
//
//                            }) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Search,
//                                    contentDescription = "ic_panel"
//                                )
//                            }
//                        }
//
//                        Image(
//                            painter = painterResource(resource = Res.drawable.depeseek_logo),
//                            contentDescription = "deepseek_logo",
//                            modifier = Modifier.size(180.dp).align(Alignment.CenterHorizontally)
//                        )
//
//                        repeat(8) {
//
//                            Card(
//                                onClick = {},
//                                colors = CardDefaults.cardColors(containerColor = Color.White)
//                            ) {
//                                Row(
//                                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    Text(
//                                        "Write a programme in C++",
//                                        color = Color.Black,
//                                        fontFamily = customFont(),
//                                        modifier = Modifier.padding(6.dp)
//                                    )
//                                    Icon(
//                                        imageVector = Icons.Filled.MoreVert,
//                                        contentDescription = "more",
//                                        modifier = Modifier.padding(6.dp)
//                                    )
//                                }
//                            }
//
//
//                        }
//                    }
//
//
//                }
//            }


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
                                    contentDescription = "ic_panel",
                                    tint = if (darkTheme) iconsDarkColor else iconsLightColor
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
                                    contentDescription = "ic_arrow_down",
                                    tint = if (darkTheme) iconsDarkColor else iconsLightColor
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
                        checked = darkTheme,
                        onCheckedChanged = {
                            viewModel.toggleTheme()
                        })


                }

                Image(
                    painter = painterResource(resource = Res.drawable.depeseek_logo),
                    contentDescription = "ic_robot",
                    modifier = Modifier
                        .size(180.dp)
                )

                Text(
                    org.jetbrains.compose.resources.stringResource(Res.string.ai_title),
                    color = if (darkTheme) Color.White else Color.Black,
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

                        ChatBubble(chat = it,darkTheme)

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
                        focusedBorderColor = if (darkTheme) Color.White else Color.Black,
                        focusedTextColor = if (darkTheme) Color.White else Color.Black,
                        unfocusedTextColor = if (darkTheme) Color.White else Color.Black,
                        unfocusedBorderColor = if (darkTheme) Color.White.copy(alpha = .7f) else Color.Black
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(resource = Res.drawable.ic_message),
                            contentDescription = "ic_message",
                            tint = if (darkTheme) iconsDarkColor else iconsLightColor
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
                                tint = if (darkTheme) iconsDarkColor else iconsLightColor
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
fun ChatBubble(chat: ChatMessage,darkTheme:Boolean) {

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
                       if (darkTheme) darkChatCardColor else  Color.LightGray.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                color = if (darkTheme) Color.White else Color.Black,
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
                            if (darkTheme) darkChatCardUser else Color.LightGray.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    SelectionContainer {
                        Text(
                            text = chat.aiResponse ?: "Error: No response",
                            color = if (darkTheme) Color.White else Color.Black,
                            fontFamily = customFont(),
                        )
                    }

                }

            }
        }

    }

}



