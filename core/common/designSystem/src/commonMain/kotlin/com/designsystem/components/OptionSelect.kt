package com.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.designsystem.theme.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OptionsSelect(entities: List<OptionSelectEntity>) {
    val selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedIndex.intValue,
        backgroundColor = Colors().background,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp),
        indicator = { tabPositions: List<TabPosition> ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex.intValue])
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Colors().outline)
                    .zIndex(1f)
            )
        },
        divider = {}
    ) {
        entities.forEachIndexed { index, item ->
            val selected = selectedIndex.intValue == index

            OptionButton(item, selected) {
                selectedIndex.intValue = index
                item.onClick.invoke()
            }
        }
    }
}

@Composable
private fun OptionButton(
    entity: OptionSelectEntity,
    selected: Boolean,
    onClick: (currencyType: String) -> Unit
) {
    val background = if (selected) Colors().outline else Colors().background
    val textColor = if (selected) Color.White else Color.Black

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(background, shape = RoundedCornerShape(30.dp))
            .clickable(
                interactionSource = null,
                indication = ripple(color = Color.Transparent)
            ) { onClick.invoke(entity.currencyType) }
            .zIndex(2f)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(25.dp)
                .padding(start = 5.dp),
            painter = painterResource(entity.icon),
            contentDescription = "",
        )

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = entity.text,
            color = textColor
        )
    }
}

data class OptionSelectEntity(
    val currencyType: String,
    val text: String,
    val icon: DrawableResource,
    val onClick: () -> Unit
)