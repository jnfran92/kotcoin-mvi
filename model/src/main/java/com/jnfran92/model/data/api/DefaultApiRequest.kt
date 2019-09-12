package com.jnfran92.model.data.api

import com.google.gson.annotations.SerializedName

/**
 * Entity base for retrieving data from the API. it returns a default JSON response
 * including: status and data fields (Entity target [T]).
 */
class DefaultApiRequest<T>(
    @SerializedName("status") var status: ApiRequestStatus,
    @SerializedName("data") var cryptoEntityList: ArrayList<T>
)

