package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

internal class MundiPaggCreditCardPayment(
        @SerializedName("recurrence")
        val recurrence :Boolean,
        @SerializedName("installments")
        val installments :Int,
        @SerializedName("card")
        val card : MundiPaggCreditCard)