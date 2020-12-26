package com.jnfran92.data.crypto.model.api

import com.google.gson.annotations.SerializedName

/**
 * Status field included by default in the JSON API response.
 */
data class ApiRequestStatus(
    @SerializedName("error_code") var error_code:Int
)