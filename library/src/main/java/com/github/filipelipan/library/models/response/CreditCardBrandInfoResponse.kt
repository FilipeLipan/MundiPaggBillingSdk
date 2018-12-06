package com.github.filipelipan.library.models.response

import com.google.gson.annotations.SerializedName

class CreditCardBrandInfoResponse(
        @SerializedName("brand")
        val brand :String,
        @SerializedName("brandName")
        val brandName :String,
        @SerializedName("gaps")
        val gaps :List<Int>,
        @SerializedName("lenghts")
        val lenghts :List<Int>,
        @SerializedName("mask")
        val mask :String,
        @SerializedName("cvv")
        val cvv :Int,
        @SerializedName("brandImage")
        val brandImage :String,
        @SerializedName("possibleBrands")
        val possibleBrands :List<String>)