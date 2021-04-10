package co.k2.newsbits.common

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CancellationException
import okhttp3.Request
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author Kislay [kislay1805@gmail.com]
 */


class NetworkUtils {

    companion object {

        const val UNKNOWN_ERROR = "unknown"
        const val JOB_CANCELLED = "cancelled"
        const val UNKNOWN_ERROR_MESSAGE = "Something went wrong"

        fun <T> processResponse(response: Response<T>): ApiResult<T, ErrorObj> {
            return try {
                if (response.isSuccessful) {
                    ApiResult(response.raw().request, response, null)
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(), ErrorObj::class.java
                    )
                    ApiResult(response.raw().request, null, error)
                }
            } catch (e: Exception) {
                Log.e("ApiError", response.raw().request.url.toString())
                e.printStackTrace()
                return handleException(e)
            }
        }

        fun <T> handleException(e: Exception): ApiResult<T, ErrorObj> {
            val request: Request? = getRequestFromException(e.cause)
            var errorCode = UNKNOWN_ERROR
            if (e is CancellationException)
                errorCode = JOB_CANCELLED
            val error = ErrorObj(UNKNOWN_ERROR_MESSAGE, errorCode, UNKNOWN_ERROR)
            return ApiResult(request, null, error)
        }

        private fun getRequestFromException(t: Throwable?): Request? {
            var request: Request? = null
            if (t != null && t is HttpException) {
                request = t.response()?.raw()?.request
            }
            return request
        }

    }

}
