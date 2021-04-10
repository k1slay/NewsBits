package co.k2.newsbits.data.models

import com.google.gson.annotations.SerializedName

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)
