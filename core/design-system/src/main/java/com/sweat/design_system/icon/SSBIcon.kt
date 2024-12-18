package com.sweat.design_system.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sweat.design_system.R
import com.sweat.design_system.theme.color.SSBColor

@Stable
@Composable
fun AddFriendIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.add_friend),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun CheckIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.check),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun CheckBoxNotSelectedIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.check_box_not_selected),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun CheckBoxSelectedIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.check_box_selected),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ChevronLeftIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.chevron_left),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ChevronRightIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.chevron_right),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun HamburgerIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.hamburger),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun HomeIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.camera),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun OutIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.out),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun PauseIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_pause),
        contentDescription = "",
        modifier = modifier,
        tint = tint
    )
}

@Stable
@Composable
fun PlayIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_play_circle),
        contentDescription = "",
        modifier = modifier,
        tint = tint
    )
}

@Stable
@Composable
fun SkipIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_skip_previous_circle),
        contentDescription = "",
        modifier = modifier,
        tint = tint
    )
}

@Stable
@Composable
fun StopIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_stop_circle),
        contentDescription = "",
        modifier = modifier,
        tint = tint
    )
}

@Stable
@Composable
fun PhotoImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.photo),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun CameraImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.camera),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ProfileSquareImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile_square),
        contentDescription = "",
        modifier = modifier
    )
}

@Composable
fun ManImage(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Image(
        painter = if (isSelected) painterResource(id = R.drawable.man_face_selected)
        else painterResource(id = R.drawable.man_face),
        contentDescription = "",
        modifier = modifier,
    )
}

@Composable
fun WomanImage(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Image(
        painter = if (isSelected) painterResource(id = R.drawable.woman_face_selected)
        else painterResource(id = R.drawable.woman_face),
        contentDescription = "",
        modifier = modifier,
    )
}

@Stable
@Composable
fun PencilIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.pencil),
        tint = color,
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SearchIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.search),
        tint = color,
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SettingIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.settings),
        tint = color,
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SwapIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.swap),
        tint = color,
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun TrashIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.trash),
        tint = color,
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ThunderIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = R.drawable.thunder),
        tint = color,
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ProfileCircleIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    Icon(
        painter = painterResource(id = R.drawable.profile_circle),
        contentDescription = "",
        modifier = modifier,
        tint = if (isSelected) SSBColor.main else Color.Unspecified
    )
}

@Composable
fun HomeIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    Icon(
        painter = painterResource(id = R.drawable.home),
        contentDescription = "",
        modifier = modifier.size(24.dp),
        tint = if (isSelected) SSBColor.main else Color.Unspecified
    )
}

@Stable
@Composable
fun DumbbellIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    Icon(
        painter = painterResource(id = R.drawable.dumbbell),
        contentDescription = "",
        modifier = modifier,
        tint = if (isSelected) SSBColor.main else Color.Unspecified
    )
}

@Composable
fun EyeIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = if (isSelected) painterResource(id = R.drawable.eye_open)
        else painterResource(id = R.drawable.eye_close),
        tint = color,
        contentDescription = "",
        modifier = modifier.size(24.dp),
    )
}

@Composable
fun HeartIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    color: Color = Color.Unspecified,
) {
    Icon(
        painter = if (isSelected) painterResource(id = R.drawable.red_heart)
        else painterResource(id = R.drawable.outlined_heart),
        tint = color,
        contentDescription = "",
        modifier = modifier.size(24.dp),
    )
}
