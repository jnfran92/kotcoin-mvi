package com.jnfran92.model.entity.api

import com.google.gson.annotations.SerializedName

/**
 * Status field included by default in the JSON API response.
 */
class ApiRequestStatusEntity(
    @SerializedName("error_code") var error_code:Int
)