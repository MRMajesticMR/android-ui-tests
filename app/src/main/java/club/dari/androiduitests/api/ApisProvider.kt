package club.dari.androiduitests.api

import club.dari.androiduitests.api.cats.CatsApi
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ApisProvider {

    val catsApi: CatsApi

}

class ApisProviderImpl(
    private val baseUrl: String, //https://cat-fact.herokuapp.com/
    private val assistApiInterceptors: List<Interceptor> = emptyList(),
    private val assistApiCustomTypeAdapters: Map<Class<*>, Any> = emptyMap()
) : ApisProvider {

    override val catsApi: CatsApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .apply {
                            assistApiCustomTypeAdapters.forEach {
                                registerTypeAdapter(it.key, it.value)
                            }
                        }
                        .create()
                )
            )
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory
                    .createWithScheduler(Schedulers.newThread())
            )
            .client(
                OkHttpClient.Builder()
                    .apply {
                        assistApiInterceptors.forEach { addInterceptor(it) }
                    }
                    .build()
            )
            .build()
            .create(CatsApi::class.java)

}