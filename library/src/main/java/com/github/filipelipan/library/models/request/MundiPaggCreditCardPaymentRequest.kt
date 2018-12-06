package com.github.filipelipan.library.models.request

import com.github.filipelipan.library.OS
import com.github.filipelipan.library.models.MissingArgumentsException
import com.github.filipelipan.library.models.entity.*

data class MundiPaggCreditCardPaymentRequest internal constructor(
        val items: List<MundiPaggCartItem>,
        val customer: MundiPaggCustomer,
        val device: MundiPaggDevice,
        val payments: List<MundiPaggCreditCardPaymentContainer>) {


    //TODO achar uma maneira melhor de
    class Builder {

        var items: List<MundiPaggCartItem>? = null
        var customerName: String? = null
        var customerEmail: String? = null
        var recurrence: Boolean? = null
        var installments: Int? = null
        var cardNumber: String? = null
        var cardHolderName: String? = null
        var expMonth: Int? = null
        var expYear: Int? = null
        var cvv: String? = null

        fun items(items: List<MundiPaggCartItem>): Builder {
            this.items = items
            return this
        }

        fun customerName(customerName: String): Builder {
            this.customerName = customerName
            return this
        }

        fun customerEmail(customerEmail: String): Builder {
            this.customerEmail = customerEmail
            return this
        }

        fun recurrence(recurrence: Boolean): Builder {
            this.recurrence = recurrence
            return this
        }

        fun installments(installments: Int) : Builder {
            this.installments = installments
            return this
        }
        fun cardNumber(cardNumber: String) : Builder{
            this.cardNumber = cardNumber
            return this
        }
        fun cardHolderName(cardHolderName: String) : Builder{
            this.cardHolderName = cardHolderName
            return this
        }
        fun expMonth(expMonth: Int) : Builder{
            this.expMonth = expMonth
            return this
        }
        fun expYear(expYear: Int) : Builder{
            this.expYear = expYear
            return this
        }
        fun cvv(cvv: String) : Builder{
            this.cvv = cvv
            return this
        }



        fun build(): MundiPaggCreditCardPaymentRequest {
            if (items == null) {
                throw MissingArgumentsException("Missing items")
            }
            if (customerName == null) {
                throw MissingArgumentsException("Missing customerName")
            }
            if (customerEmail == null) {
                throw MissingArgumentsException("Missing customerEmail")
            }
            if (recurrence == null) {
                throw MissingArgumentsException("Missin recurrence")
            }
            if (installments == null) {
                throw MissingArgumentsException("Missin installments")
            }
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

            return MundiPaggCreditCardPaymentRequest(
                    items!!,
                    MundiPaggCustomer(name = customerName, email = customerEmail),
                    MundiPaggDevice(OS),
                    payments = listOf(MundiPaggCreditCardPaymentContainer(paymentMethod = "credit_card",
                            creditCard = MundiPaggCreditCardPayment(
                                    recurrence = recurrence!!,
                                    installments = installments!!,
                                    card = MundiPaggCreditCard(
                                            number = cardNumber!!,
                                            holderName = cardHolderName!!,
                                            expMonth = expMonth!!,
                                            expYear = expYear!!,
                                            cvv = cvv!!)))))
        }
    }
}
