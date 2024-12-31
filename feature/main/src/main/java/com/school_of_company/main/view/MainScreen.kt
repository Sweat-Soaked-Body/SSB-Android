package com.school_of_company.main.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.school_of_company.main.view.component.ExerciseBottomSheet
import com.school_of_company.main.view.component.ExerciseSet
import com.school_of_company.main.view.component.Food
import com.school_of_company.main.view.component.MainCalendar
import com.school_of_company.main.view.component.MainTabRowItem
import com.school_of_company.main.viewmodel.MainIntent
import com.school_of_company.main.viewmodel.MainSideEffect
import com.school_of_company.main.viewmodel.MainState
import com.school_of_company.main.viewmodel.MainViewModel
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.PlusIcon
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
    navigateToFood: () -> Unit,
    navigateToTimer: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = currentDate.format(formatter)
    val swipeRefreshLoading by viewModel.swipeRefreshLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = swipeRefreshLoading)

    LaunchedEffect(Unit) {
        viewModel.handleIntent(MainIntent.ExerciseRoutineCheck(date = formattedDate))
        viewModel.handleIntent(MainIntent.FoodRoutineCheck(date = formattedDate))
    }

    LaunchedEffect(lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is MainSideEffect.ExerciseRoutineCheckSuccess -> {

                    }
                    is MainSideEffect.ExerciseRoutineCheckFailed -> {

                    }
                }
            }
        }
    }

    MainScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
        navigateToFood = navigateToFood,
        swipeRefreshState = swipeRefreshState,
        loadStuff = viewModel::loadStuff,
        navigateToTimer = navigateToTimer
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainState,
    pagerState: PagerState = rememberPagerState(pageCount = { 2 }),
    handleIntent: (MainIntent) -> Unit,
    swipeRefreshState: SwipeRefreshState,
    loadStuff: () -> Unit,
    navigateToFood: () -> Unit,
    navigateToTimer: ()-> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    SSBAndroidTheme { colors, typography ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                loadStuff()
            }
        ) {
            if (state.isShowExerciseBottomSheet) {
                ModalBottomSheet(
                    containerColor = colors.white,
                    sheetState = bottomSheetState,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    onDismissRequest = { handleIntent(MainIntent.HideBottomSheet) },
                ) {
                    ExerciseBottomSheet(
                        onClickTimer = {
                            navigateToTimer()
                        },
                        onClickDelete = {
                            handleIntent(MainIntent.DeleteExerciseRoutine(routineId = state.currentRoutineId))
                        }
                    )
                }
            }

            Scaffold(
                modifier = modifier.fillMaxSize(),
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = colors.gray50)
                            .padding(paddingValues)
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
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(
                                                        index
                                                    )
                                                }
                                            },
                                        )
                                    }
                                }
                            }
                        }

                        // Calendar Component
                        MainCalendar()

                        // Content Pager
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
                                                exerciseState = setStateList,
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
                                                    Log.d("ExerciseSet", "onStateChange called with set=${setStateList.id}, minute=$minute, second=$second, weight=$weight, count=$count")
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
                },
                floatingActionButton = {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = colors.main,
                                shape = RoundedCornerShape(size = 25.dp)
                            )
                            .padding(9.dp)
                    ) {
                        PlusIcon(
                            modifier = Modifier
                                .size(32.dp)
                                .clickableSingle { navigateToFood() },
                            tint = colors.white
                        )
                    }
                },
                floatingActionButtonPosition = FabPosition.End
            )
        }
    }
}