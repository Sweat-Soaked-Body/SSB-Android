package com.school_of_company.food.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.component.topbar.SSBTopBar
import com.sweat.design_system.icon.CameraImage
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.icon.PhotoImage
import com.sweat.design_system.icon.SearchIcon
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
internal fun AddFoodRoute(
    popUpBackStack: () -> Unit,
    navigateToFoodSearch: () -> Unit
) {

}

@Composable
private fun AddFoodScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit,
    navigateToFoodSearch: () -> Unit
) {
    SSBAndroidTheme { colors, typography ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.white)
                .padding(top = 15.dp)
        ) {

            SSBTopBar(
                startIcon = { ChevronLeftIcon(modifier = Modifier.clickableSingle { popUpBackStack() }) },
                betweenText = "식단 추가",
                endIcon = { SearchIcon(modifier = Modifier.clickableSingle { navigateToFoodSearch() }) },
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            Spacer(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = colors.gray100
                    )
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = colors.gray50, shape = RoundedCornerShape(size = 8.dp))
                        .weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CameraImage()

                        Text(
                            text = "사진으로 찍기",
                            style = typography.label,
                            color = colors.black
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = colors.gray50, shape = RoundedCornerShape(size = 8.dp))
                        .weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        PhotoImage()

                        Text(
                            text = "갤러리에서 선택",
                            style = typography.label,
                            color = colors.black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            SSBButton(
                text = "식단 추가하기",
                onClick = {  },
                state = ButtonState.Enabled, // Temporary
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 36.dp
                    )
            )
        }
    }
}

@Preview
@Composable
private fun AddFoodScreenPreview() {
    AddFoodScreen(
        popUpBackStack = {},
        navigateToFoodSearch = {}
    )

}