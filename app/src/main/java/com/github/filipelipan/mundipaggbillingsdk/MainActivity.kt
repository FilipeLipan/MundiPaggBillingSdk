package com.github.filipelipan.mundipaggbillingsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.filipelipan.library.MundiPaggBillingSdk
import com.github.filipelipan.library.models.request.MundiPaggBankBillPaymentRequest
import com.github.filipelipan.library.models.request.MundiPaggCartItem
import com.github.filipelipan.library.models.request.MundiPaggCreditCardPaymentRequest
import com.github.filipelipan.library.models.request.MundiPaggCreditCardTokenRequest
import com.github.filipelipan.library.models.response.CreditCardBrandInfoResponse
import com.github.filipelipan.library.models.response.CreditCardTokenResponse
import com.github.filipelipan.library.models.response.PaymentResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

public class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.canonicalName
    val compositeDisposable :CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mundiPaggBillingSdk = MundiPaggBillingSdk(application, mundiPaggBase64Key = "", publicKey = "")

        val request = MundiPaggBankBillPaymentRequest.Builder()
                .items(listOf(MundiPaggCartItem(amount = 2990, description = "Chaveiro do Tesseract", quantity =  1)))
                .customerName("Tony Stark")
                .customerEmail("avengerstark@ligadajustica.com.br")
                .bank("033")
                .instructions("Pagar até o vencimento")
                .dueAt("2020-09-20T00:00:00Z")
                .documentNumber("123").build()


        compositeDisposable.add(mundiPaggBillingSdk.makePaymentWithBankBill(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<PaymentResponse>(){
                    override fun onComplete() {}

                    override fun onNext(response: PaymentResponse) {
                        Log.e(TAG,"call sucess:" + response.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG,"call error", e)
                    }
                }))

        val requestCreditCard = MundiPaggCreditCardPaymentRequest.Builder()
                .items(listOf(MundiPaggCartItem(amount = 2990, description = "Chaveiro do Tesseract", quantity =  1)))
                .customerName("Tony Stark")
                .customerEmail("avengerstark@ligadajustica.com.br")
                .recurrence(false)
                .installments(1)
                .cardNumber("342793631858229")
                .cardHolderName("Tony Stark")
                .expMonth(1)
                .expYear(20)
                .cvv("3531").build()

        compositeDisposable.add(mundiPaggBillingSdk.makePaymentWithCreditCard(requestCreditCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<PaymentResponse>(){
                    override fun onComplete() {}

                    override fun onNext(response: PaymentResponse) {
                        Log.e(TAG,"call sucess:" + response.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG,"call error", e)
                    }
                }))


        compositeDisposable.add(
                mundiPaggBillingSdk.getCreditCardBrandInfo("5232")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object: DisposableObserver<CreditCardBrandInfoResponse>(){
                            override fun onComplete() {}

                            override fun onNext(response: CreditCardBrandInfoResponse) {
                                Log.e(TAG,"call sucess:" + response.toString())
                            }

                            override fun onError(e: Throwable) {
                                Log.e(TAG,"call error", e)
                            }
                        }))


        compositeDisposable.add(
                mundiPaggBillingSdk.getCreditCardBrandInfo("5232")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object: DisposableObserver<CreditCardBrandInfoResponse>(){
                            override fun onComplete() {}

                            override fun onNext(response: CreditCardBrandInfoResponse) {
                                Log.e(TAG,"call sucess:" + response.toString())
                            }

                            override fun onError(e: Throwable) {
                                Log.e(TAG,"call error", e)
                            }
                        }))


        val mundiPaggCreditCardTokenRequest = MundiPaggCreditCardTokenRequest.Builder()
                .cardNumber("342793631858229")
                .cardHolderName("Tony Stark")
                .expMonth(1)
                .expYear(20)
                .cvv("3531").build()


        compositeDisposable.add(
                mundiPaggBillingSdk.getCreditCardToken(mundiPaggCreditCardTokenRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object: DisposableObserver<CreditCardTokenResponse>(){
                            override fun onComplete() {}

                            override fun onNext(response: CreditCardTokenResponse) {
                                Log.e(TAG,"call sucess:" + response.toString())
                            }

                            override fun onError(e: Throwable) {
                                Log.e(TAG,"call error", e)
                            }
                        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
