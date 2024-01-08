package com.example.iotsystemparking.config

import com.example.iotsystemparking.model.ModelAverage
import com.example.iotsystemparking.model.ModelPendapatan
import com.example.iotsystemparking.model.ModelSlot
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ConfigPendapatan {

    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://backendparkol-m77laoox7a-uc.a.run.app/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getMotorService() = getRetrofit().create(MotorApiService::class.java)
    fun getMobilService() = getRetrofit().create(MobilApiService::class.java)
    fun getSlot1Service() = getRetrofit().create(Slot1Service::class.java)
    fun getSlot2Service() = getRetrofit().create(Slot2Service::class.java)
    fun getAverageService() = getRetrofit().create(AverageService::class.java)
}

interface MotorApiService {

    @GET("totalpengunjung-hari/motor")
    fun getMotorData(): Call<ModelPendapatan>
}

interface MobilApiService {

    @GET("totalpengunjung-hari/mobil")
    fun getMobilData(): Call<ModelPendapatan>
}

interface Slot1Service {

    @GET("total-kendaraan-lantai-1")
    fun getSlot1Data(): Call<ModelSlot>
}

interface Slot2Service {

    @GET("total-kendaraan-lantai-2")
    fun getSlot2Data(): Call<ModelSlot>
}

interface AverageService {

    @GET("average1")
    fun getAverageData(): Call<ModelAverage>
}
