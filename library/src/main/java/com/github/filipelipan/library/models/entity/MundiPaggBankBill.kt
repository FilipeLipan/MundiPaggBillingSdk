package com.github.filipelipan.library.models.entity

import com.google.gson.annotations.SerializedName

internal class MundiPaggBankBill(
        @SerializedName("bank")
        val bank :String,
        @SerializedName("instructions")
        val instructions :String,
        @SerializedName("due_at")
        val dueAt :String,
        @SerializedName("document_number")
        val documentNumber :String)