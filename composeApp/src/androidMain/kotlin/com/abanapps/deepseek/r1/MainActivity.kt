package com.abanapps.deepseek.r1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abanapps.deepseek.r1.presentation.utils.customFont
import deepseekr1.composeapp.generated.resources.Res
import deepseekr1.composeapp.generated.resources.ic_panel
import org.jetbrains.compose.resources.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview(showSystemUi = true, backgroundColor = 0xFF4478a9)
@Composable
fun Testings(modifier: Modifier = Modifier) {

    Scaffold(
        containerColor = Color(0xFF4478a9)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(330.dp)
                .padding(it)
                .background(Color(0xFFFFFDFD))
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_panel),
                            contentDescription = "ic_panel"
                        )
                    }

                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "ic_panel"
                        )
                    }
                }

                repeat(8) {

                    Card(
                        onClick = {},
                        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                                color = Color.Black,
                                fontFamily = customFont(),
                                modifier = Modifier.padding(6.dp)
                            )
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "more",
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }


                }
            }


        }
    }


}


