package com.school_of_company.food.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBAndroidTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun FoodList(
    modifier: Modifier = Modifier,
    data: ImmutableList<FoodData> = persistentListOf()
) {
    SSBAndroidTheme { colors, _ ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.white)
                .padding(horizontal = 24.dp)
        ) {
            itemsIndexed(data) { _, item ->
                FoodListItem(
                    modifier = modifier,
                    item = item
                )
            }
        }
    }
}