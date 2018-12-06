package com.github.filipelipan.library.models.request

import com.google.gson.annotations.SerializedName

public class MundiPaggCartItem(
        @SerializedName("amount")
        val amount :Long,
        @SerializedName("description")
        val description :String,
        @SerializedName("quantity")
        val quantity :Int)