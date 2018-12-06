package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

internal class MundiPaggBankBillPaymentContainer(
        paymentMethod :String,
        @SerializedName("boleto")
        val boleto : MundiPaggBankBill) : MundiPaggPayment(paymentMethod = paymentMethod)
