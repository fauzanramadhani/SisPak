package com.ndc.sispak.ui.component.app_bar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun BottomNavigationBar(
    bottomNavigationItems: List<BottomNavigationItem>,
    selectedIndex: Int,
    onSelectedIndexChange: (index: Int) -> Unit
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    BottomAppBar(
        containerColor = Color.Transparent,
    ) {
        bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
            with(bottomNavigationItem) {
                val isSelected = selectedIndex == index
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        onSelectedIndexChange(index)
                    },
                    label = {
                        Text(
                            text = stringResource(id = label),
                            style = typography.labelSmall
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(
                                id = if (isSelected) selectedIcon
                                else unselectedIcon
                            ),
                            contentDescription = ""
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = color.primary,
                        unselectedIconColor = color.secondary,
                        selectedTextColor = color.primary,
                        unselectedTextColor = color.secondary,
                        indicatorColor = color.primaryContainer
                    )
                )
            }
        }
    }
}