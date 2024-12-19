package com.sweat.network.mapper.auth.request

import com.sweat.model.param.auth.LoginRequestParam
import com.sweat.network.dto.auth.request.LoginRequest

fun LoginRequestParam.toDto(): LoginRequest =
    LoginRequest(
        username = this.username,
        password = this.password
    )