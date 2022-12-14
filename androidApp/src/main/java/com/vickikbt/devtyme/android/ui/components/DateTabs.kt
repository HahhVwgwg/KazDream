package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.devtyme.android.utils.toPresentation

@Composable
fun DatesTabs(
    modifier: Modifier = Modifier,
    dates: List<String>,
    selectedTab: Int?,
    onTabItemClick: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab ?: 0,
        backgroundColor = MaterialTheme.colors.primary,
        indicator = {
            ItemTabIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    it[selectedTab ?: 0]
                )
            )
        }
    ) {
        dates.forEachIndexed { index, tabItem ->
            val isSelected = selectedTab == index

            Tab(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(10.dp)),
                selected = selectedTab == index,
                onClick = { onTabItemClick(index) },
                text = {
                    Text(
                        text = tabItem.toPresentation(),
                        color = if (isSelected) Color.Black else MaterialTheme.colors.onPrimary,
                        fontSize = if (isSelected) 14.sp else 14.sp,
                        style = MaterialTheme.typography.h5,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}
