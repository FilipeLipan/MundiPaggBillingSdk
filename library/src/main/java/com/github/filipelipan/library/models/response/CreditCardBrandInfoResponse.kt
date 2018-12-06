package com.github.filipelipan.library.models.response

class CreditCardBrandInfoResponse(
        val brand :String,
        val brandName :String,
        val gaps :List<Int>,
        val lenghts :List<Int>,
        val mask :String,
        val cvv :String,
        val brandImage :String,
        val possibleBrands :List<String>)