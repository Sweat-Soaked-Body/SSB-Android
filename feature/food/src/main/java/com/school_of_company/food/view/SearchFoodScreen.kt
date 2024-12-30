package com.school_of_company.food.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school_of_company.food.view.component.FoodData
import com.school_of_company.food.view.component.FoodList
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.icon.SearchIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.exercise.view.component.ExerciseTextField
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SearchFoodRoute(
    popUpBackStack: () -> Unit
) {

    SearchFoodScreen(popUpBackStack = popUpBackStack)
}

@Composable
private fun SearchFoodScreen(
    modifier: Modifier = Modifier,
    popUpBackStack: () -> Unit
) {
    SSBAndroidTheme { colors, typography ->

        val foodDataList = persistentListOf(
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
            FoodData("마라탕", "100g", "100kcal"),
        )

        val (textState, onTextChange) = rememberSaveable { mutableStateOf("마라탕") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.white)
                .padding(top = 15.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {

                ChevronLeftIcon(
                    modifier = Modifier
                        .clickableSingle { popUpBackStack() }
                        .padding(end = 10.dp)
                )

                ExerciseTextField(
                    text = "음식을 검색해주세요",
                    textState = textState,
                    onTextChange = onTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                SearchIcon(
                    modifier = Modifier
                        .clickableSingle { }
                        .padding(start = 10.dp)
                ) // todo : Check Food -> Next Logic
            }

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

            Spacer(modifier = Modifier.padding(top = 15.dp))

            FoodList(
                data = foodDataList
            )
        }
    }
}

@Preview
@Composable
private fun SearchFoodScreenPreview() {
    SearchFoodScreen(
        popUpBackStack = {}
    )
}