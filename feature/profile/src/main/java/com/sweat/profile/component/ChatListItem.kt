package com.sweat.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.model.friend.FriendModel

@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    state: FriendModel,
    onClick: (Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .clickableSingle { onClick(state.id) }
    ) {
        Row {
            Image(
                painter = painterResource(com.sweat.design_system.R.drawable.profile_square), // getProfileImage(state.image),
                contentDescription = "chat partner profile",
                modifier = Modifier
                    .padding(8.dp)
                    .size(45.dp),
            )
            Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 5.dp)) {
                Text(
                    text = state.friend,
                    style = SSBTypography.bodyMedium,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "읽지 않은 메시지가 1개 있습니다", // TODO:  
                    style = SSBTypography.label,
                    fontWeight = FontWeight(400),
                    color = SSBColor.gray600
                )
//                Text(
//                    text = state.message,
//                    style = SSBTypography.label,
//                    fontWeight = FontWeight(400),
//                    color = if (state.isReadMessage) Color(0xFF000000)
//                    else SSBColor.gray600
//                )
            }
        }
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "오늘",
                style = SSBTypography.label,
                fontWeight = FontWeight(400),
                color = SSBColor.gray500,
                textAlign = TextAlign.Right,
            )

//            Text(
//                text = state.date,
//                style = SSBTypography.label,
//                fontWeight = FontWeight(400),
//                color = SSBColor.gray500,
//                textAlign = TextAlign.Right,
//            )
        }
    }
}


