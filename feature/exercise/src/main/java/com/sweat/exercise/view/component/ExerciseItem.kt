package com.sweat.exercise.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.HeartIcon
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun ExerciseItem(
    modifier: Modifier,
    text: String,
    isSelected: Boolean = false,
    onHeartClick: () -> Unit
){
    SSBAndroidTheme { colors, typography ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 17.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = typography.bodySmall
            )
            Spacer(modifier = modifier.weight(1f))
            HeartIcon(
                modifier = modifier
                    .padding(1.dp)
                    .size(24.dp)
                    .clickableSingle { onHeartClick() },
                isSelected = isSelected
            )
        }
        Divider(
            thickness = 1.dp,
            color = colors.gray100
        )
    }
}

@Preview
@Composable
fun ExerciseItemPreview(){
    ExerciseItem(
        modifier = Modifier,
        text = "바벨 백스쿼트",
        isSelected = true,
        onHeartClick = {}
    )
}