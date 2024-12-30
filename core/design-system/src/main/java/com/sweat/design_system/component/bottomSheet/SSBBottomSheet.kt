package com.sweat.design_system.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.icon.OutIcon
import com.sweat.design_system.icon.PencilIcon
import com.sweat.design_system.icon.TrashIcon
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SSBBottomSheet(
    modifier: Modifier = Modifier,
    bottomSheetItems: @Composable ColumnScope.() -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(31.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFFFFF), // TODO: 컬러 추가
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .padding(top = 20.dp, bottom = 80.dp)
    ) {
        bottomSheetItems()
    }
}

@Preview
@Composable
fun SSBBottomSheetPreview() {
    SSBBottomSheet(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomSheetItem(
            icon = { PencilIcon() },
            title = "프로필 수정",
            textColor = SSBColor.gray600
        )
        BottomSheetItem(
            icon = { OutIcon() },
            title = "로그 아웃",
            textColor = SSBColor.gray600
        )
        BottomSheetItem(
            icon = { TrashIcon() },
            title = "프로필 삭제",
            textColor = SSBColor.error
        )
    }
}
