package com.github.filipelipan.library.models.request

import com.github.filipelipan.library.models.MissingArgumentsException
import com.github.filipelipan.library.models.entity.MundiPaggCreditCard
import com.google.gson.annotations.SerializedName

data class MundiPaggCreditCardTokenRequest internal constructor(
        @SerializedName("type")
        val type: String = "card",
        @SerializedName("card")
        val mundiPaggCardDetail: MundiPaggCreditCard) {

    class Builder {

        var cardNumber: String? = null
        var cardHolderName: String? = null
        var expMonth: Int? = null
        var expYear: Int? = null
        var cvv: String? = null


        fun cardNumber(cardNumber: String): Builder {
            this.cardNumber = cardNumber
            return this
        }

        fun cardHolderName(cardHolderName: String): Builder {
            this.cardHolderName = cardHolderName
            return this
        }

        fun expMonth(expMonth: Int): Builder {
            this.expMonth = expMonth
            return this
        }

        fun expYear(expYear: Int): Builder {
            this.expYear = expYear
            return this
        }

        fun cvv(cvv: String): Builder {
            this.cvv = cvv
            return this
        }

        fun build(): MundiPaggCreditCardTokenRequest {

            if (cardNumber == null) {
                throw MissingArgumentsException("Missin cardNumber")
            }
            if (cardHolderName == null) {
                throw MissingArgumentsException("Missin cardHolderName")
            }
            if (expMonth == null) {
                throw MissingArgumentsException("Missin expMonth")
            }
            if (expYear == null) {
                throw MissingArgumentsException("Missin expYear")
            }
            if (cvv == null) {
                throw MissingArgumentsException("Missin cvv")
            }

            return MundiPaggCreditCardTokenRequest(
                    mundiPaggCardDetail = MundiPaggCreditCard(
                            number = cardNumber!!,
                            holderName = cardHolderName!!,
                            expMonth = expMonth!!,
                            expYear = expYear!!,
                            cvv = cvv!!))
        }
    }
}