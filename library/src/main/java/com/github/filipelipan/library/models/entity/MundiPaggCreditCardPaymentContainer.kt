package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

internal class MundiPaggCreditCardPaymentContainer(
        paymentMethod :String,
        @SerializedName("credit_card")
        val creditCard : MundiPaggCreditCardPayment) : MundiPaggPayment(paymentMethod)
