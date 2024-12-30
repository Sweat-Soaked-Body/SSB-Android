package com.school_of_company.main.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.model.entity.main.FoodEntity
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FoodItemList(
    modifier: Modifier = Modifier,
    items: ImmutableList<FoodEntity>
) {
    SSBAndroidTheme { colors, typography ->
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(items) { item ->
                Text(
                    text = "· ${item.name}",
                    style = typography.label,
                    color = colors.black,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }
        }
    }
}
