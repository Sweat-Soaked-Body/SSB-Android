package com.sweat.common.network

import com.sweat.common.action.*
import com.sweat.common.exception.*

fun Throwable.errorHandling(unKnownAction: () -> Unit = { unKnownExceptionAction() }) {
    errorHandlers[this::class.java]?.invoke() ?: unKnownAction()
}

private val errorHandlers = mapOf<Class<out Throwable>, () -> Unit> (
    BadRequestException::class.java to { badRequestExceptionAction() },
    UnauthorizedException::class.java to { unauthorizedExceptionAction() },
    TokenExpirationException::class.java to { tokenExpirationExceptionAction() },
    ForBiddenException::class.java to { forbiddenExceptionAction() },
    NotFoundException::class.java to { notFoundExceptionAction() },
    NotAcceptableException::class.java to { notAcceptableExceptionAction() },
    TimeOutException::class.java to { timeOutExceptionAction() },
    ConflictException::class.java to { conflictExceptionAction() },
    TooManyRequestException::class.java to { tooManyRequestExceptionAction() },
    ServerException::class.java to { serverExceptionAction() },
    NoInternetException::class.java to { noInternetExceptionAction() },
    OtherHttpException::class.java to { otherHttpExceptionAction() },
)