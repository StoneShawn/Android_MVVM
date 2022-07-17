package d.ishigishou.shawn_mvvm.network

import d.ishigishou.shawn_mvvm.api.ApiService
import d.ishigishou.shawn_mvvm.api.UrlConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    val service:ApiService by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit().create(ApiService::class.java)
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UrlConstant.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    private fun addHeaderInterceptor(): Interceptor{
        return Interceptor { chain->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .method(originalRequest.method,originalRequest.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }


    private fun getOkHttpClient(): OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //Debug模式要改成不顯示
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(addHeaderInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60L,TimeUnit.SECONDS)
            .readTimeout(60L,TimeUnit.SECONDS)
            .writeTimeout(60L,TimeUnit.SECONDS)
            .build()

    }
}