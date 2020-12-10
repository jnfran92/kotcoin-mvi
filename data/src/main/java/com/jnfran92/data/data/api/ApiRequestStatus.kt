package com.jnfran92.data.data.api

import com.google.gson.annotations.SerializedName

/**
 * Status field included by default in the JSON API response.
 */
class ApiRequestStatus(
    @SerializedName("error_code") var error_code:Int
)