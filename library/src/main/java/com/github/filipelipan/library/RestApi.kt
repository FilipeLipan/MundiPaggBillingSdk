package com.github.filipelipan.library

import com.github.filipelipan.library.models.request.MundiPaggCreditCardTokenRequest
import com.github.filipelipan.library.models.request.MundiPaggBankBillPaymentRequest
import com.github.filipelipan.library.models.request.MundiPaggCreditCardPaymentRequest
import com.github.filipelipan.library.models.response.CreditCardBrandInfoResponse
import com.github.filipelipan.library.models.response.CreditCardTokenResponse
import com.github.filipelipan.library.models.response.PaymentResponse
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {


    @POST("core/v1/orders")
    fun makePaymentWithCreditCard(
            @Body request: MundiPaggCreditCardPaymentRequest,
            @Header(AUTH) authorization :String): Observable<PaymentResponse>

    @POST("core/v1/orders")
    fun makePaymentWithBankBill(
            @Body request: MundiPaggBankBillPaymentRequest,
            @Header(AUTH) authorization :String): Observable<PaymentResponse>


    @GET("bin/v1/{fourDigits}")
    fun getCreditCardBrandInfo(
            @Path("fourDigits") fourDigits :String) : Observable<CreditCardBrandInfoResponse>

    @POST("core/v1/tokens")
    fun getCreditCardToken(
            @Query("appId") publicKey :String,
            @Body mundiPaggCreditCardTokenRequest : MundiPaggCreditCardTokenRequest) : Observable<CreditCardTokenResponse>
}