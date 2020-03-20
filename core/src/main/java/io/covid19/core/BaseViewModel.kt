package io.covid19.core

import androidx.lifecycle.ViewModel
import io.covid19.data.network.RequestErrorHandler
import io.covid19.data.network.Result
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    suspend fun <T> tryResult(call: suspend () -> T): Result<T> {
        return try {
            Result.Success(data = call())
        } catch (e: Exception) {
            Result.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}