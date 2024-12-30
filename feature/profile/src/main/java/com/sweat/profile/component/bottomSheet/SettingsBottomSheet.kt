package com.sweat.profile.component.bottomSheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sweat.design_system.component.bottomSheet.BottomSheetItem
import com.sweat.design_system.component.bottomSheet.SSBBottomSheet
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.OutIcon
import com.sweat.design_system.icon.PencilIcon
import com.sweat.design_system.icon.TrashIcon
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SettingsBottomSheet(
    modifier: Modifier = Modifier,
    onProfileEditClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onLeaveClick: () -> Unit,
) {
    SSBBottomSheet(modifier = modifier) {
        BottomSheetItem(
            modifier = Modifier.clickableSingle { onProfileEditClick() },
            icon = { PencilIcon() },
            title = "프로필 수정",
            textColor = SSBColor.gray600
        )
        BottomSheetItem(
            modifier = Modifier.clickableSingle { onLogoutClick() },
            icon = { OutIcon() },
            title = "로그 아웃",
            textColor = SSBColor.gray600
        )
        /*BottomSheetItem(
            modifier = Modifier.clickableSingle { onLeaveClick() },
            icon = { TrashIcon() },
            title = "회원 탈퇴",
            textColor = SSBColor.error
        )*/
    }
}