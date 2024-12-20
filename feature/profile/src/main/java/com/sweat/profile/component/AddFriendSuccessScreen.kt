package com.sweat.profile.component

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AddFriendSuccessScreen(
    modifier: Modifier = Modifier,
    friendName: String,
    context: Context,
    popupBackStack: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            ChevronLeftIcon(modifier = Modifier.clickableSingle(onClick = popupBackStack))
        }
        Spacer(modifier = Modifier.height(34.dp))
        AddFriendSuccessCard(friendName = friendName, context = context)
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun AddFriendSuccessScreenPreview() {
    AddFriendSuccessScreen(
        friendName = "친구이름",
        context = LocalContext.current,
        popupBackStack = {}
    )
}