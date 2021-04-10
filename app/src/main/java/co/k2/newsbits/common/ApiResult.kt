package co.k2.newsbits.common

import okhttp3.Request
import retrofit2.Response

data class ApiResult<T, E>(
    val request: Request?,
    val response: Response<T>?,
    val error: E?
) {
    inline val body: T?
        get() = response?.body()
}
