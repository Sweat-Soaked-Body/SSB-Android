package com.school_of_company.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.HamburgerIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.model.entity.main.FoodEntity
import com.sweat.model.entity.main.FoodRoutineResponseEntity
import kotlinx.collections.immutable.ImmutableList

@Composable
fun Food(
    modifier: Modifier = Modifier,
    foodType: FoodRoutineResponseEntity,
    state: ImmutableList<FoodEntity>,
) {
    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colors.white, RoundedCornerShape(size = 12.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = foodType.type,
                    style = typography.bodyMedium,
                    color = colors.black
                )

                HamburgerIcon(modifier = Modifier.clickableSingle { /*TODO*/ })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(180.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = foodType.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                FoodItemList(items = state)
            }
        }
    }
}