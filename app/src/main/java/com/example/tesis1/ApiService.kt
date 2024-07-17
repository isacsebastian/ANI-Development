package com.example.tesis1

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("api/get_last_transcription/")
    fun getLastTranscription(): Call<ResponseBody>

    @Multipart
    @POST("api/convert_api/")
    fun uploadAudio(@Part audio: MultipartBody.Part): Call<ResponseBody>
}
