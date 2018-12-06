package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

public class MundiPaggCreditCardToken(
        @SerializedName("last_four_digits")
        val lastFourDigits :String,
        @SerializedName("holder_name")
        val holderName :String,
        @SerializedName("exp_month")
        val expMonth :Int,
        @SerializedName("exp_year")
        val expYear :Int,
        @SerializedName("brand")
        val brand :String)