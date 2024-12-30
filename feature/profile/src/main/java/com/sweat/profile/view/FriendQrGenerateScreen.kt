package com.sweat.profile.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.profile.component.QRCodeGenerator

@Composable
fun FriendQrGenerateRoute(
    modifier: Modifier = Modifier,
    myName: String,
    popupBackStack: () -> Unit,
) {
    FriendQrGenerateScreen(
        modifier = modifier,
        myName = myName,
        popupBackStack = popupBackStack,
    )
}

@Composable
fun FriendQrGenerateScreen(
    modifier: Modifier = Modifier,
    myName: String,
    popupBackStack: () -> Unit,
) {
    SSBAndroidTheme { _, typo ->
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                ChevronLeftIcon(modifier = Modifier.clickableSingle(onClick = popupBackStack))
            }
            Spacer(modifier = Modifier.height(30.dp))
            QRCodeGenerator(content = myName)
            Spacer(modifier = Modifier.height(34.dp))
            Text(
                text = "내 QR을 다른 사람한테\n공유해보세요!",
                style = typo.titleSmall,
                fontWeight = FontWeight.W600,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun FriendQrGenerateScreenPreview() {
    FriendQrGenerateScreen(
        popupBackStack = {},
        myName = "",
    )
}