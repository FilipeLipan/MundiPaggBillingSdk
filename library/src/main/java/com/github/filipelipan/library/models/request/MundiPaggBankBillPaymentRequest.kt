package com.github.filipelipan.library.models.request

import com.github.filipelipan.library.OS
import com.github.filipelipan.library.models.MissingArgumentsException
import com.github.filipelipan.library.models.entity.MundiPaggBankBill
import com.github.filipelipan.library.models.entity.MundiPaggBankBillPaymentContainer
import com.github.filipelipan.library.models.entity.MundiPaggCustomer
import com.github.filipelipan.library.models.entity.MundiPaggDevice

public data class MundiPaggBankBillPaymentRequest internal constructor(
        val items: List<MundiPaggCartItem>,
        val customer: MundiPaggCustomer,
        val device: MundiPaggDevice,
        val payments: List<MundiPaggBankBillPaymentContainer>) {

    class Builder {
        var items: List<MundiPaggCartItem>? = null
        var customerName: String? = null
        var customerEmail: String? = null
        var bank: String? = null
        var instructions: String? = null
        var dueAt: String? = null
        var documentNumber: String? = null

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

        fun bank(bank: String): Builder {
            this.bank = bank
            return this
        }

        fun instructions(instructions: String): Builder {
            this.instructions = instructions
            return this
        }

        fun dueAt(dueAt: String): Builder {
            this.dueAt = dueAt
            return this
        }

        fun documentNumber(documentNumber: String): Builder {
            this.documentNumber = documentNumber
            return this
        }

        fun build(): MundiPaggBankBillPaymentRequest {

            if (items == null) { throw MissingArgumentsException("Missing items") }
            if (customerName == null) { throw MissingArgumentsException("Missing customerName") }
            if (customerEmail == null) { throw MissingArgumentsException("Missing customerEmail") }
            if (bank == null) { throw MissingArgumentsException("Missing bank") }
            if (instructions == null) { throw MissingArgumentsException("Missing instructions") }
            if (dueAt == null) { throw MissingArgumentsException("Missing dueAt") }
            if (documentNumber == null) { throw MissingArgumentsException("Missing documentNumber") }

            return MundiPaggBankBillPaymentRequest(
                    items!!,
                    MundiPaggCustomer(name = customerName, email = customerEmail),
                    MundiPaggDevice(OS),
                    listOf(MundiPaggBankBillPaymentContainer(paymentMethod = "boleto",
                            boleto = MundiPaggBankBill(
                                    bank = bank!!,
                                    instructions = instructions!!,
                                    dueAt = dueAt!!,
                                    documentNumber = documentNumber!!))))
        }
    }
}