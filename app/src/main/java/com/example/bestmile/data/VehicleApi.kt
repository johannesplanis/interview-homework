package com.example.bestmile.data

import com.example.bestmile.data.model.Vehicle
import com.example.bestmile.data.model.VehicleDetails
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object VehicleApiProvider {
    private val okHttpClient = OkHttpClient.Builder()
        .let { builder ->
            builder.addInterceptor {
                val interceptedRequest = it.request()
                    .newBuilder()
                    .header("x-api-key", "1Dk7bLkWIG2PEnMIGarLc76p9Dfh7tpu2FFpvLgR")
                    .build()

                it.proceed(interceptedRequest)
            }
            builder.build()
        }

    const val BASE_URL = "https://5w53f1x8oa.execute-api.eu-west-1.amazonaws.com/"

    val api: VehicleApi by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VehicleApi::class.java)
    }
//
//    val mockApi: VehicleApi by lazy {
//        object: VehicleApi {
//            override suspend fun getVehicleDetails(id: String): VehicleDetails {
//                delay(500)
//                return VehicleDetails(
//                    id = id,
//                    location = Location(
//                        latitude = 46.5223916 * (Math.random() / 1000 + 1),
//                        longitude = 6.6314437 * (Math.random() / 1000 + 1)
//                    )
//                )
//            }
//
//            override suspend fun getVehicles(): List<Vehicle> {
//                delay(500)
//                return listOf(
//                    Vehicle("22f5b382-bb3c-4b84-bd1f-b5e3b2b602f9"),
//                    Vehicle("82897c0e-0d52-4503-b5c5-5fcdcf3772d4"),
//                    Vehicle("1db1d7b9-b3bb-4535-a726-207e45117b20"),
//                    Vehicle("a0d8430f-1530-47ac-878d-28c1b0e66f18")
//                )
//            }
//        }
//    }
}

interface VehicleApi {
    @GET("/dev/vehicles/{id}")
    suspend fun getVehicleDetails(@Path("id") id: String): VehicleDetails

    @GET("/dev/vehicles")
    suspend fun getVehicles(): List<Vehicle>
}