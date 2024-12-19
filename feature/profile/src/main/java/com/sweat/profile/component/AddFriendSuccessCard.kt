package com.sweat.profile.component

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.sweat.design_system.theme.SSBAndroidTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AddFriendSuccessCard(
    modifier: Modifier = Modifier,
    friendName: String,
    context: Context,
) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory()) // GIF 지원 추가
        }
        .build()

    SSBAndroidTheme { color, typography ->
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("file:///android_asset/right_mark.gif") // 로컬 GIF 경로
                    .size(Size.ORIGINAL) // 원본 크기로 로드
                    .build(),
                contentDescription = "GIF 이미지",
                imageLoader = imageLoader,
            )
            Text(
                text = "${friendName}님과 친구 완료!",
                style = typography.titleSmall,
                fontWeight = FontWeight.W600,
                color = Color.Black,
            )
        }
    }
}