package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

public class MundiPaggCreditCard(
        @SerializedName("number")
        val number :String,
        @SerializedName("holder_name")
        val holderName :String,
        @SerializedName("exp_month")
        val expMonth :Int,
        @SerializedName("exp_year")
        val expYear :Int,
        @SerializedName("cvv")
        val cvv :String)
