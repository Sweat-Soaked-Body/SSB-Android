package com.sweat.profile.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.profile.viewModel.ChattingIntent
import com.sweat.profile.viewModel.ChattingState
import com.sweat.profile.viewModel.ChattingViewModel

@Composable
fun ChattingRoute(
    modifier: Modifier = Modifier,
    viewModel: ChattingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ChattingIntent.InitializeWebSocket("1"))  // 웹소켓 URL 초기화
    }

    ChattingScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
    )
}

@Composable
fun ChattingScreen(
    modifier: Modifier = Modifier,
    state: ChattingState,
    handleIntent: (ChattingIntent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

    }
}

@Preview
@Composable
fun PreviewChattingScreen() {
    ChattingScreen(
        handleIntent = { _ -> },
        state = ChattingState.getInitialState(),
    )
}
