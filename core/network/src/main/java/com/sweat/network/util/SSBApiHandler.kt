package com.sweat.network.util

import com.sweat.common.exception.*
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SSBApiHandler<T> {
    private lateinit var httpRequest: suspend () -> T

    fun httpRequest(httpRequest: suspend () -> T) =
        this.apply { this.httpRequest = httpRequest }

    suspend fun sendRequest(): T {
        return try {
            httpRequest.invoke()
        } catch (e: HttpException) {
            val message = e.message
            throw when(e.code()) {
                400 -> BadRequestException(
                    message = message
                )
                401 -> UnauthorizedException(
                    message = message
                )
                403 -> ForBiddenException(
                    message = message
                )
                404 -> NotFoundException(
                    message = message
                )
                409 -> ConflictException(
                    message = message
                )
                500, 501, 502, 503 -> ServerException(
                    message = message
                )
                else -> OtherHttpException(
                    message = message,
                    code = e.code()
                )
            }
        } catch (e: SocketTimeoutException) {
            throw TimeOutException(message = e.message)
        } catch (e: UnknownHostException) {
            throw NoInternetException()
        } catch (e: TokenExpirationException) {
            throw TokenExpirationException()
        } catch (e: Exception) {
            throw UnKnownException(message = e.message)
        }
    }
}