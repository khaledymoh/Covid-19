package io.covid19.data.network

import io.covid19.data.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object RequestErrorHandler {

    fun getRequestError(throwable: Throwable): ResultException {
        return when (throwable) {
            is HttpException -> {
                handleHttpException(throwable)
            }
            is IOException -> {
                ResultException.Connection(R.string.error_no_connectivity_message)
            }
            is SocketTimeoutException ->  {
                ResultException.Timeout(R.string.error_timeout_message)
            }
            else -> {
                ResultException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): ResultException {
        return when (httpException.code()) {
            in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {
                ResultException.Client(R.string.error_client_message)
            }
            in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {
                ResultException.Server(R.string.error_server_message)
            }
            else -> {
                ResultException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }

    private const val HTTP_CODE_CLIENT_START = 400
    private const val HTTP_CODE_CLIENT_END = 499
    private const val HTTP_CODE_SERVER_START = 500
    private const val HTTP_CODE_SERVER_END = 599
}