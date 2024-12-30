package com.school_of_company.food.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sweat.design_system.theme.SSBAndroidTheme

data class FoodData(
    val name: String,
    val gram: String,
    val kcal: String
)

@Composable
internal fun FoodListItem(
    modifier: Modifier = Modifier,
    item: FoodData
) {
    SSBAndroidTheme { colors, typography ->

        Column(modifier = modifier.fillMaxWidth()) {

            Text(
                text = item.name,
                style = typography.bodyMedium,
                color = colors.black
            )

            Row {
               Text(
                   text = item.gram,
                   style = typography.label,
                   color = colors.gray500
               )

                Text(
                    text = " · ",
                    style = typography.label,
                    color = colors.gray500
                )

                Text(
                    text = item.kcal,
                    style = typography.label,
                    color = colors.gray500
                )
            }
        }
    }
}

@Preview
@Composable
private fun FoodListItemPreview() {
    FoodListItem(item = FoodData("햄버거", "100g", "100kcal"))
}