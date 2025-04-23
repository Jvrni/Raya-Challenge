package com.raya_challenge.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.designsystem.extensions.passwordTransformation
import com.domain.models.Transaction
import org.jetbrains.compose.resources.painterResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.ic_bitcoin

@Composable
fun CardTransaction(transaction: Transaction, showBalance: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(Res.drawable.ic_bitcoin),
                contentDescription = "",
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = transaction.title,
                    color = Color.Black,
                    style = TextStyle(fontWeight = FontWeight.Medium).copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )

                Text(
                    text = transaction.date,
                    color = Color.Black,
                    fontSize = 12.sp,
                    style = TextStyle.Default.copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )
            }

            Text(
                text = "$${transaction.value.toString().passwordTransformation(!showBalance)}",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
        }
    }
}