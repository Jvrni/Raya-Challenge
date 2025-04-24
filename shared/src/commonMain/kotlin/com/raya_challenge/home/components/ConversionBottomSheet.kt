package com.raya_challenge.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import raya_challenge.shared.generated.resources.Res
import raya_challenge.shared.generated.resources.ic_arrow_right_duo
import raya_challenge.shared.generated.resources.ic_close


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionBottomSheet(
    entity: ConversionBottomSheetEntity,
    modalBottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    onChangeCurrency: (value: String) -> Unit,
    onChangeCrypto: (value: String) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = modalBottomSheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 24.dp),
                    text = "Conversion",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    style = TextStyle(fontWeight = FontWeight.Medium).copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both,
                        )
                    )
                )

                Image(
                    modifier = Modifier.size(30.dp).clip(CircleShape)
                        .clickable { onDismissRequest.invoke() },
                    painter = painterResource(Res.drawable.ic_close),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 22.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    shape = CircleShape,
                    value = entity.currencyValue,
                    onValueChange = { onChangeCurrency.invoke(it) },
                    leadingIcon = {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(entity.currencyIcon),
                            contentDescription = "",
                        )
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 12.dp),
                    painter = painterResource(Res.drawable.ic_arrow_right_duo),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    shape = CircleShape,
                    value = entity.cryptoValue,
                    onValueChange = { onChangeCrypto.invoke(it) },
                    leadingIcon = {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(entity.toCryptoIcon),
                            contentDescription = "",
                        )
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
        }
    }
}

data class ConversionBottomSheetEntity(
    val currencyValue: String,
    val currencyIcon: DrawableResource,
    val cryptoValue: String,
    val toCryptoIcon: DrawableResource,
)
