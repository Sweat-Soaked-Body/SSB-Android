package com.meister.profile.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sweat.design_system.icon.OutIcon
import com.sweat.design_system.icon.TrashIcon
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SettingsBottomSheet(modifier: Modifier = Modifier) {
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
            .padding(vertical = 40.dp)
    ) {
        ProfileBottomSheetItem(
            icon = { OutIcon() }, // TODO: 아이콘 변경
            title = "내 QR코드 보기",
            textColor = SSBColor.gray600
        )
        ProfileBottomSheetItem(
            icon = { OutIcon() },// TODO: 아이콘 변경
            title = "QR 코드로 친구 추가",
            textColor = SSBColor.gray600
        )
        ProfileBottomSheetItem(
            icon = { TrashIcon() },// TODO: 아이콘 변경
            title = "NFC로 친구 추가",
            textColor = SSBColor.gray600
        )
    }
}
