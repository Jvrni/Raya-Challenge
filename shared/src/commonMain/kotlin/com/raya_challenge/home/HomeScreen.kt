package com.raya_challenge.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raya_challenge.home.components.CardBalance
import com.raya_challenge.home.components.CardTransaction
import com.designsystem.theme.Colors
import com.raya_challenge.home.components.HeaderOptions
import com.raya_challenge.home.contract.HomeContract

@Composable
fun HomeScreen(state: HomeContract.State, viewModel: HomeViewModel) {
    Scaffold(
        modifier = Modifier.background(Colors().background),
        topBar = {
            HeaderOptions(modifier = Modifier.padding(horizontal = 16.dp)) { id ->

            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors().background)
                .padding(paddingValues)
                .padding(16.dp),
        ) {

            item { CardBalance() }

            item {
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom = 8.dp),
                    text = "Transactions",
                    style = TextStyle(fontWeight = FontWeight.SemiBold).copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    ),
                    color = Color.Black,
                    fontSize = 22.sp,
                )
            }

            items(5) { CardTransaction() }
        }
    }
}
