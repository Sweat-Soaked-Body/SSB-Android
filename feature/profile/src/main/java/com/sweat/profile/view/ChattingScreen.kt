package com.sweat.profile.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.profile.component.ChatMessageItem
import com.sweat.profile.component.ChattingTextField
import com.sweat.profile.viewModel.ChattingIntent
import com.sweat.profile.viewModel.ChattingState
import com.sweat.profile.viewModel.ChattingViewModel

@Composable
fun ChattingRoute(
    modifier: Modifier = Modifier,
    viewModel: ChattingViewModel = hiltViewModel(),
    id: String,
    popUpBackStack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ChattingIntent.InitializeWebSocket(id))  // 웹소켓 URL 초기화
    }

    ChattingScreen(
        modifier = modifier,
        state = state,
        handleIntent = viewModel::handleIntent,
        popUpBackStack = popUpBackStack,
    )
}

@Composable
fun ChattingScreen(
    modifier: Modifier = Modifier,
    state: ChattingState,
    handleIntent: (ChattingIntent) -> Unit,
    popUpBackStack: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = SSBColor.white)
            .padding(bottom = 16.dp)
            .padding(horizontal = 12.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 15.dp)
            ) {
                ChevronLeftIcon(modifier = Modifier.clickableSingle { popUpBackStack() })
                Text(
                    text = "문혜성",
                    style = SSBTypography.subTitle,
                    fontWeight = FontWeight(600),
                    color = SSBColor.black,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(24.dp))
            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = SSBColor.gray100,
                thickness = 1.dp
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.messages) { message ->
                ChatMessageItem(
                    message = message,
                    myName = state.myName,
                )
            }
        }
        ChattingTextField(
            textState = state.messageInputTextState,
            updateTextValue = { handleIntent(ChattingIntent.SetMessageInputTextState(it)) },
            onclick = { handleIntent(ChattingIntent.SendMessage(message = state.messageInputTextState)) }
        )
    }
}

@Preview
@Composable
fun PreviewChattingScreen() {
    ChattingScreen(
        handleIntent = { _ -> },
        state = ChattingState.getInitialState(),
        popUpBackStack = {}
    )
}
