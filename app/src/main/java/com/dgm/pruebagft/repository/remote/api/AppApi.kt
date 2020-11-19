package com.dgm.pruebagft.repository.remote.api

import com.dgm.pruebagft.repository.remote.model.DetailsResponse
import com.dgm.pruebagft.repository.remote.model.CatalogResponse
import com.dgm.pruebagft.repository.remote.model.SuccesResponse
import com.dgm.pruebagft.repository.remote.model.User
import retrofit2.http.*

interface AppApi {


    @POST("api/auth/user/create")
    suspend fun registerUser(@Body user: User): SuccesResponse

    @FormUrlEncoded
    @POST("api/auth/user/authenticate")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): SuccesResponse

    @GET("api/catalogs/cards")
    suspend fun getCatalog(@Header("x-access-token") token: String): CatalogResponse

    @FormUrlEncoded
    @POST("api/accounts")
    suspend fun addCard(
        @Header("x-access-token") token: String?,
        @Field("userId") userId: String?,
        @Field("type") type: String,
        @Field("name") name: String
    ): SuccesResponse

    @GET("api/accounts")
    suspend fun getCardInfo(@Header("x-access-token") token: String?): DetailsResponse


}