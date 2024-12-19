package com.sweat.network.mapper.auth.request

import com.sweat.model.param.auth.SignUpRequestParam
import com.sweat.network.dto.auth.request.SignUpRequest

fun SignUpRequestParam.toDto() : SignUpRequest =
    SignUpRequest(
        username = this.username,
        password = this.password,
        name = this.name,
        sex = this.sex,
        age = this.age,
        weight = this.weight,
        height = this.height
    )