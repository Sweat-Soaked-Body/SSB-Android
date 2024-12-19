package com.sweat.model.param.auth

data class SignUpRequestParam(
    val username: String,
    val password: String,
    val name: String,
    val sex: String,
    val age: Int,
    val weight: Int,
    val height: Int,
)
