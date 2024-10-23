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
fun CameraImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.camera),
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
fun PauseImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.pause),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SkipImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.skip),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun StopImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.stop),
        contentDescription = "",
        modifier = modifier
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
fun ProfileSquareImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile_square),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun PencilIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.pencil),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SearchIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.search),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SettingIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.settings),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun SwapIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.swap),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun TrashIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.trash),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ThunderIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.thunder),
        contentDescription = "",
        modifier = modifier
    )
}

@Stable
@Composable
fun ProfileCircleIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
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
    isSelected: Boolean = false
) {
    Icon(
        painter = painterResource(id = R.drawable.home),
        contentDescription = "Show Profile Icon",
        modifier = modifier.size(24.dp),
        tint = if (isSelected) SSBColor.main else Color.Unspecified
    )
}

@Stable
@Composable
fun DumbbellImage(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
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
    isSelected: Boolean = false
) {
    Image(
        painter = if (isSelected) painterResource(id = R.drawable.eye_open)
        else painterResource(id = R.drawable.eye_close),
        contentDescription = "Show Profile Icon",
        modifier = modifier.size(24.dp),
    )
}

@Composable
fun HeartIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Image(
        painter = if (isSelected) painterResource(id = R.drawable.red_heart)
        else painterResource(id = R.drawable.outlined_heart),
        contentDescription = "Show Profile Icon",
        modifier = modifier.size(24.dp),
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
        contentDescription = "Show Profile Icon",
        modifier = modifier.size(24.dp),
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
        contentDescription = "Show Profile Icon",
        modifier = modifier.size(24.dp),
    )
}