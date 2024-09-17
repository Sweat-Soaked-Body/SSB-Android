package com.sweat.common.action

fun badRequestExceptionAction() {
    println("Handling BadRequest")
}

fun unauthorizedExceptionAction() {
    println("Handling Unauthorized")
}

fun tokenExpirationExceptionAction() {
    println("Handling TokenExpiration")
}

fun forbiddenExceptionAction() {
    println("Handling Forbidden")
}

fun notFoundExceptionAction() {
    println("Handling NotFound")
}

fun notAcceptableExceptionAction() {
    println("Handling NotAcceptable")
}

fun timeOutExceptionAction() {
    println("Handling TimeOut")
}

fun conflictExceptionAction() {
    println("Handling Conflict")
}

fun tooManyRequestExceptionAction() {
    println("Handling TooManyRequest")
}

fun serverExceptionAction() {
    println("Handling Server")
}

fun noInternetExceptionAction() {
    println("Handling NoInternet")
}

fun otherHttpExceptionAction() {
    println("Handling OtherHttpException")
}

fun unKnownExceptionAction() {
    println("Handling UnKnown")
}