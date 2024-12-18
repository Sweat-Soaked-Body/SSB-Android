package com.sweat.profile.component.bottomSheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sweat.design_system.component.bottomSheet.BottomSheetItem
import com.sweat.design_system.component.bottomSheet.SSBBottomSheet
import com.sweat.design_system.icon.OutIcon
import com.sweat.design_system.icon.TrashIcon
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SettingsBottomSheet(modifier: Modifier = Modifier) {
    SSBBottomSheet(modifier = modifier) {
        BottomSheetItem(
            icon = { OutIcon() }, // TODO: 아이콘 변경
            title = "내 QR코드 보기",
            textColor = SSBColor.gray600
        )
        BottomSheetItem(
            icon = { OutIcon() },// TODO: 아이콘 변경
            title = "QR 코드로 친구 추가",
            textColor = SSBColor.gray600
        )
        BottomSheetItem(
            icon = { TrashIcon() },// TODO: 아이콘 변경
            title = "NFC로 친구 추가",
            textColor = SSBColor.gray600
        )
    }
}