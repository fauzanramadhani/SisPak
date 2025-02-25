package com.ndc.sispak.data.remote.service.system.forward_chaining

import com.ndc.sispak.common.BaseResponse
import com.ndc.sispak.data.remote.body.forward_chaining.DiseaseBody
import com.ndc.sispak.data.remote.body.forward_chaining.SymptomBody
import com.ndc.sispak.data.remote.response.forward_chaining.DiseaseResponse
import com.ndc.sispak.data.remote.response.forward_chaining.SymptomResponse
import com.ndc.sispak.data.remote.response.forward_chaining.SymptomWithDiseaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ForwardChainingService {
    @POST("/system/forward_chaining/update_symptoms")
    suspend fun updateSymptoms(
        @Query("system_id") systemId: Int,
        @Body symptoms: List<SymptomBody>
    ): Response<BaseResponse<String>>

    @GET("/system/forward_chaining/symptoms")
    suspend fun getSymptoms(
        @Query("system_id") systemId: Int,
    ) : Response<BaseResponse<List<SymptomResponse>>>

    @POST("/system/forward_chaining/update_diseases")
    suspend fun updateDiseases(
        @Query("system_id") systemId: Int,
        @Body symptoms: List<DiseaseBody>
    ): Response<BaseResponse<String>>

    @GET("/system/forward_chaining/diseases")
    suspend fun getDiseases(
        @Query("system_id") systemId: Int,
    ) : Response<BaseResponse<List<DiseaseResponse>>>

    @GET("/system/forward_chaining/symptom_with_disease")
    suspend fun getSymptomWithDisease(
        @Query("system_id") systemId: Int,
    ) : Response<BaseResponse<List<SymptomWithDiseaseResponse>>>
}