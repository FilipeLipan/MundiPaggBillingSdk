package com.github.filipelipan.library

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


/**
 * Created by dnso
 */
object AppInject {

    private const val CONTEXT = "applicationContext"
    const val READ_TIMEOUT = 120L
    const val CONNECT_TIMEOUT = 120L
    const val TOKEN = "x-access-token"
    const val ACCEPT = "Accept"
    const val CONTENT_TYPE = "Content-Type"
    const val CONTENT_TYPE_JSON = "application/json"

    const val TAG_TRUST_MANAGER = "trust_manager_error";
    const val TAG_SSL_SOCKET = "ssl_socket_error";

    fun modules(): List<Module> = listOf(
            applicationModule
    )

    private val applicationModule: Module = module {

        single { getOkHttpClient(androidApplication()) }
        single { getGson() }
        single { getRestApi(get(), get()) }
        single { PaymentWithCreditCard(get()) }
    }


    fun getOkHttpClient(context: Context): OkHttpClient {

        val trustManager = provideX509TrustManager(context)
        val sc = SSLContext.getInstance("TLSv1.2")
        if(trustManager != null ){
            sc.init(null, arrayOf<TrustManager>(trustManager), null);
        }else{
            sc.init(null, null, null)
        }

        val httpClientBuilder = OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)

        if(trustManager != null && sc != null){
            httpClientBuilder.sslSocketFactory(Tls12SocketFactory(sc.socketFactory), trustManager)
        }

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return httpClientBuilder.build()
    }

    fun getGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    fun getRestApi(httpClient: OkHttpClient, gson: Gson): RestApi {

//        var trustManager :X509TrustManager? = provideX509TrustManager(androidApplication);
//        var sslSocketFactory :TLSSocketFactory? = null
//        trustManager?.let {
//            sslSocketFactory = provideSSLSocketFactory(trustManager);
//        }


        //TODO implement TSL 1.2
        val retrofitClient: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.mundipagg.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofitClient.create(RestApi::class.java)
    }



//    fun provideSSLSocketFactory(trustManagerTLSSocketFactory :X509TrustManager) : TLSSocketFactory?  {
//        try {
//            return TLSSocketFactory();
//        } catch (e : KeyManagementException) {
//            Log.e(TAG_SSL_SOCKET, e.message);
//        } catch (e : NoSuchAlgorithmException) {
//            Log.e(TAG_SSL_SOCKET, e.message);
//        }
//        return null;
//    }

    private fun provideX509TrustManager(context: Context): X509TrustManager? {
        val trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }
}