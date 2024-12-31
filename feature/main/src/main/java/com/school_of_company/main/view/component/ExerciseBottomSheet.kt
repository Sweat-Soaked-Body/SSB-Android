package com.school_of_company.main.view.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sweat.design_system.component.bottomSheet.BottomSheetItem
import com.sweat.design_system.component.bottomSheet.SSBBottomSheet
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ThunderIcon
import com.sweat.design_system.icon.TrashIcon
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun ExerciseBottomSheet(
    modifier: Modifier = Modifier,
    onClickTimer: () -> Unit,
    onClickDelete: () -> Unit
) {
    SSBBottomSheet {
        BottomSheetItem(
            modifier = modifier.clickableSingle { onClickTimer() },
            icon = { ThunderIcon() },
            title = "운동 시작하기",
            textColor = SSBColor.gray600
        )

        BottomSheetItem(
            modifier= modifier.clickableSingle { onClickDelete() },
            icon = { TrashIcon() },
            title = "운동 삭제",
            textColor = SSBColor.error
        )
    }
}