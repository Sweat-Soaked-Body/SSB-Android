package com.sweat.ssb_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sweat.common.exception.NoInternetException
import com.sweat.common.exception.OtherHttpException
import com.sweat.common.exception.ServerException
import com.sweat.common.exception.TimeOutException
import com.sweat.common.exception.UnKnownException
import com.sweat.design_system.R
import com.sweat.login.loginRoute
import com.sweat.profile.addFriendWithQRRoute
import com.sweat.profile.profileRoute
import com.sweat.signup.navigation.navigateToSignupRoute
import com.sweat.signup.navigation.signupRoute
import com.sweat.ui.BottomSheetType
import com.sweat.ui.makeToast
import kotlinx.coroutines.CoroutineScope

@Composable
fun SSBNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showBottomSheet: CoroutineScope.(BottomSheetType) -> Unit,
    hideBottomSheet: CoroutineScope.() -> Unit,
    startDestination: String = loginRoute,
) {
    val context = LocalContext.current

    val makeErrorToast: (throwable: Throwable?, message: Int?) -> Unit = { throwable, message ->
        val errorMessage = throwable?.let {
            when (it) {
                is TimeOutException -> R.string.error_time_out
                is ServerException -> R.string.error_server
                is NoInternetException -> R.string.error_no_internet
                is OtherHttpException -> R.string.error_other_http
                is UnKnownException -> R.string.error_un_known
                else -> message
            }
        } ?: message ?: R.string.error_default
        makeToast(context, context.getString(errorMessage))
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        profileRoute(
            navigateToAddFriendWithQR = {},
            navigateToChat = {},
            navigateToLogin = {},
            navigateToMyQR = {},
            navigateToAddFriendWithNFC = {},
            showBottomSheet = showBottomSheet,
            hideBottomSheet = hideBottomSheet,
        )

        signupRoute(
            navigateToMain = navController::navigateToSignupRoute,
            makeErrorToast = makeErrorToast,
            popUpBackStack = navController::popBackStack
        )

        loginRoute(
            navigateToMain = {},
            navigateToSignup = navController::navigateToSignupRoute
        )

        addFriendWithQRRoute(
            navigateToProfile = {},
            popUpBackStack = {},
        )
    }
}
