<<<<<<<< HEAD:core/network/src/main/java/com/sweat/network/mapper/auth/request/LoginRequestMapper.kt
package com.sweat.network.mapper.auth.request
========
package com.sweat.network.mapper.auth
>>>>>>>> 2d3943cd7146e56251ba2614453fdf04c8fa67a8:core/network/src/main/java/com/sweat/network/mapper/auth/LoginRequestMapper.kt

import com.sweat.model.param.auth.LoginRequestParam
import com.sweat.network.dto.auth.request.LoginRequest

fun LoginRequestParam.toDto(): LoginRequest =
    LoginRequest(
        username = this.username,
        password = this.password
    )