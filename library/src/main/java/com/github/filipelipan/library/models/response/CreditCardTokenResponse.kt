package com.github.filipelipan.library.models.response

import com.github.filipelipan.library.models.entity.MundiPaggCreditCardToken
import com.google.gson.annotations.SerializedName

class CreditCardTokenResponse(
        @SerializedName("id")
        val id :String,
        @SerializedName("type")
        val type :String,
        @SerializedName("created_at")
        val createdAt :String,
        @SerializedName("expires_at")
        val expiresAt :String,
        @SerializedName("card")
        val card : MundiPaggCreditCardToken)