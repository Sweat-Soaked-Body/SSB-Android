package com.school_of_company.main.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.school_of_company.main.view.component.ExerciseSet
import com.school_of_company.main.view.component.Food
import com.school_of_company.main.view.component.MainCalendar
import com.school_of_company.main.view.component.MainTabRowItem
import com.school_of_company.main.viewmodel.MainIntent
import com.school_of_company.main.viewmodel.MainState
import com.school_of_company.main.viewmodel.MainViewModel
import com.sweat.design_system.theme.SSBAndroidTheme
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = currentDate.format(formatter)

    LaunchedEffect(Unit) {
        viewModel.handleIntent(MainIntent.ExerciseRoutineCheck(date = formattedDate))
        viewModel.handleIntent(MainIntent.FoodRoutineCheck(date = formattedDate))
    }

    MainScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainState,
    pagerState: PagerState = rememberPagerState(pageCount = { 2 }),
    handleIntent: (MainIntent) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colors.gray50)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
                    .background(color = colors.white)
                    .border(width = 1.dp, color = colors.gray100)
                    .padding(horizontal = 24.dp)
                    .padding(top = 13.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "오늘의 루틴",
                    style = typography.titleSmall,
                    color = colors.black
                )

                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    TabRow(
                        modifier = Modifier.width(86.dp),
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                color = colors.black,
                                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            )
                        },
                        divider = {},
                        containerColor = colors.white
                    ) {
                        persistentListOf(
                            "운동",
                            "식단"
                        ).forEachIndexed { index, title ->
                            MainTabRowItem(
                                selected = index == pagerState.currentPage,
                                title = title,
                                onClick = {
                                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                                },
                            )
                        }
                    }
                }
            }

            MainCalendar()

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.setList) { setStateList ->
                                ExerciseSet(
                                    exerciseName = setStateList,
                                    state = setStateList.sets.toImmutableList(),
                                    handleIntent = handleIntent,
                                    onSetChange = { set, minute, second, weight, count ->
                                        handleIntent(
                                            MainIntent.UpdateSet(
                                                id = setStateList.id,
                                                set = set,
                                                minute = minute,
                                                second = second,
                                                weight = weight,
                                                count = count
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }

                    1 -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                        ) {
                            items(state.foodList) { foodStateList ->
                                Food(
                                    foodType = foodStateList,
                                    state = foodStateList.food.toImmutableList()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}