package co.k2.newsbits.data.source

import co.k2.newsbits.common.ApiResult
import co.k2.newsbits.common.ErrorObj
import co.k2.newsbits.data.models.ApiResponse

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
interface RemoteDataSource {
    suspend fun getHeadlines(country: String): ApiResult<ApiResponse, ErrorObj>
}
