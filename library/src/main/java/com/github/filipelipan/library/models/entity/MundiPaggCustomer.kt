package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

internal class MundiPaggCustomer(
        @SerializedName("name")
        val name :String?,
        @SerializedName("email")
        val email :String?)