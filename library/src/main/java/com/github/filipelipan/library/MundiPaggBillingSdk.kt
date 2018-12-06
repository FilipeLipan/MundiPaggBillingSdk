package com.github.filipelipan.library

import android.app.Application
import com.github.filipelipan.library.models.request.MundiPaggBankBillPaymentRequest
import com.github.filipelipan.library.models.request.MundiPaggCreditCardPaymentRequest
import com.github.filipelipan.library.models.request.MundiPaggCreditCardTokenRequest
import com.github.filipelipan.library.models.response.CreditCardBrandInfoResponse
import com.github.filipelipan.library.models.response.CreditCardTokenResponse
import com.github.filipelipan.library.models.response.PaymentResponse
import io.reactivex.Observable
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MundiPaggBillingSdk(val applicationContext: Application, val publicKey: String, val mundiPaggBase64Key: String) {

    private val injectResolver: InjectResolver by lazy { InjectResolver() }

    init {
        applicationContext.startKoin(applicationContext, AppInject.modules())
    }

    fun makePaymentWithCreditCard(request: MundiPaggCreditCardPaymentRequest): Observable<PaymentResponse> {
        return injectResolver.makePaymentWithCreditCard(request)
    }

    fun makePaymentWithBankBill(request: MundiPaggBankBillPaymentRequest): Observable<PaymentResponse> {
        return injectResolver.makePaymentWithBankBill(request)
    }


    fun getCreditCardBrandInfo(fourDigits :String): Observable<CreditCardBrandInfoResponse> {
        return injectResolver.getCreditCardBrandInfo(fourDigits)
    }

    fun getCreditCardToken(mundiPaggCreditCardTokenRequest : MundiPaggCreditCardTokenRequest): Observable<CreditCardTokenResponse> {
        return injectResolver.getCreditCardToken(mundiPaggCreditCardTokenRequest)
    }

    private inner class InjectResolver : KoinComponent {

        private val restApi by inject<RestApi>()
        fun makePaymentWithCreditCard(request: MundiPaggCreditCardPaymentRequest): Observable<PaymentResponse> {
            return restApi.makePaymentWithCreditCard(request, authorization = "$AUTH_TYPE $mundiPaggBase64Key")
        }

        fun makePaymentWithBankBill(request: MundiPaggBankBillPaymentRequest): Observable<PaymentResponse> {
            return restApi.makePaymentWithBankBill(request, authorization = "$AUTH_TYPE $mundiPaggBase64Key")
        }


        fun getCreditCardBrandInfo(fourDigits :String): Observable<CreditCardBrandInfoResponse> {
            return restApi.getCreditCardBrandInfo(fourDigits.substring(IntRange(0, Math.min(3, fourDigits.length -1))))
        }

        fun getCreditCardToken(mundiPaggCreditCardTokenRequest : MundiPaggCreditCardTokenRequest): Observable<CreditCardTokenResponse> {
            return restApi.getCreditCardToken(publicKey= publicKey, mundiPaggCreditCardTokenRequest = mundiPaggCreditCardTokenRequest)
        }
    }
}