package com.meister.profile.component.bottomSheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sweat.design_system.component.bottomSheet.BottomSheetItem
import com.sweat.design_system.component.bottomSheet.SSBBottomSheet
import com.sweat.design_system.icon.OutIcon
import com.sweat.design_system.icon.PencilIcon
import com.sweat.design_system.icon.TrashIcon
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun AddFriendBottomSheet(modifier: Modifier = Modifier) {
    SSBBottomSheet(modifier = modifier) {
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