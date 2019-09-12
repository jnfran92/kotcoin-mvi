package com.jnfran92.model.entity.api

import com.google.gson.annotations.SerializedName
import com.jnfran92.model.entity.CryptoEntity

/**
 * Entity base for retrieving data from the API. it returns a default JSON response
 * including: status and data fields (Entity target [T]).
 */
class DefaultApiRequestEntity<T>(
    @SerializedName("status") var status: ApiRequestStatusEntity,
    @SerializedName("data") var cryptoEntityList: ArrayList<T>
)

