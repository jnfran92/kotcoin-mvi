package com.jnfran92.data.crypto.model.api

import com.google.gson.annotations.SerializedName

/**
 * Entity base for retrieving data from the API. It returns a default JSON response
 * including: status and data fields (Entity target [T]).
 */
data class DefaultApiRequest<T>(
    @SerializedName("status") var status: ApiRequestStatus,
    @SerializedName("data") var data: ArrayList<T>
)

