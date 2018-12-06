package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

internal open class MundiPaggPayment(
        @SerializedName("payment_method")
        open val paymentMethod :String)
