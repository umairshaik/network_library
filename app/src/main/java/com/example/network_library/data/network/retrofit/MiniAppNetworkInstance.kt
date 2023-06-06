package com.example.network_library.data.network.retrofit

import com.example.network.NetworkPayloadResponse
import com.example.network.di.createWebService
import com.example.network_library.data.model.Series
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header

interface MiniAppApi {
    @GET(value = "/api/v3/page/series")
    suspend fun getTrendingMovies(

        @Header("webPlatform")
        webPlatform: String = "878a6db06e0cd079b3b02408d246801d217c018f",
        @Header("ChannelID")
        VMP: String = "VMP",
        @Header("TRANSACTIONID")
        TRANSACTIONID: String = "A908111111111111"
    ): NetworkPayloadResponse<Series>
}

@Singleton
class MiniAppNetworkInstance @Inject constructor(
   private val  retrofit: Retrofit
) {

    private val networkApi: MiniAppApi = createWebService(retrofit)

    suspend fun getMovies(): NetworkPayloadResponse<Series> {
        return networkApi.getTrendingMovies(/*idtoken = "eyJ0eXAiOiJKV1QiLCJraWQiOiJ3VTNpZklJYUxPVUFSZVJCL0ZHNmVNMVAxUU09IiwiYWxnIjoiUlMyNTYifQ.eyJhdF9oYXNoIjoiYXczdmFLeC1fdmpzSFVybVRDM2ZDUSIsInN1YiI6ImVhYTg4NGUyLTI0MzQtNDQyYS1hMzllLTBjZjg3YjY2ZGVkZCIsImF1ZGl0VHJhY2tpbmdJZCI6ImNhY2ZkMDJmLTBlZjMtNDk4Yy04NTQ1LWM5M2FjMWJkNTg4Ny0yNzkwMTIiLCJpc3MiOiJodHRwczovL2FtOjQ0My9hbS9vYXV0aDIvdHNlbC9teXRlbGtvbXNlbC9saXRlIiwidG9rZW5OYW1lIjoiaWRfdG9rZW4iLCJzaWQiOiJteENsVGcySncxZVlGT0p6TnBCcGRnbFM0MURieUUzUUhiOUtFYjJnYXpFPSIsImFjciI6IjAiLCJpZGVudGlmaWVySUQiOiIrNjI4MTIxOTUzNTY4MiIsImF6cCI6ImVmN2UyNTQwNmFkZmZmZDI1NGViNjFiYWQ1YjUyYzk4IiwiaWRlbnRUeXBlIjoibW9iaWxlIiwiYXV0aF90aW1lIjoxNjg2MDQ4MDg5LCJleHAiOjE2ODYwNTE2OTAsIm1zaXNkbiI6Iis2MjgxMjE5NTM1NjgyIiwiaWF0IjoxNjg2MDQ4MDkwLCJvbGRVaWQiOiJlYWE4ODRlMi0yNDM0LTQ0MmEtYTM5ZS0wY2Y4N2I2NmRlZGQiLCJzdWJuYW1lIjoiZWFhODg0ZTItMjQzNC00NDJhLWEzOWUtMGNmODdiNjZkZWRkIiwiZ2l2ZW5fbmFtZSI6IlNyaXJhbSIsIm5vbmNlIjoidHJ1ZSIsImF1ZCI6ImVmN2UyNTQwNmFkZmZmZDI1NGViNjFiYWQ1YjUyYzk4IiwiY19oYXNoIjoiQkhmOGtpU0VGX01JTkNpZ0kxZERGUSIsIm9yZy5mb3JnZXJvY2sub3BlbmlkY29ubmVjdC5vcHMiOiJnWEJaWDZZZ05RLTlyMHJPcC13TzM0VHdNWGciLCJyZWFsbSI6Ii90c2VsL215dGVsa29tc2VsL2xpdGUiLCJ0b2tlblR5cGUiOiJKV1RUb2tlbiIsImZhbWlseV9uYW1lIjoiTiJ9.snMrTQN2ZRDMmVX0ccAmMC7SQ7RJdO6P1yEarhS3VuJT3AqoEqCeelgM-AsCbLOIoXTNCEOKm1WiPKyNw3JOA0abvUrFGnkRF_zg6Yyt3HZkg42CA-M4yDyihY4Ckry65r8eGPbCpdgh86F0zvOvSRhIaRYoB2rYPSGbGWkuVgC2XGGBNmzCKFNldJdhlVly7mEJ690v4Evf1layO6ObxRz7fxQwHNtRNrc_kaoLDgOPr8DJjsSAce5RUbhTEANlyMvhhIkEY9vs2TfL7KnYswXHqVuTQEn4BISXTtKE5Gmloxekm4pnDMGtt6AbmV9-J4qrNMgihkbAU3SbuzngHA",
            accessToken = "Bearer: eyJ0eXAiOiJKV1QiLCJraWQiOiJ3VTNpZklJYUxPVUFSZVJCL0ZHNmVNMVAxUU09IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJlYWE4ODRlMi0yNDM0LTQ0MmEtYTM5ZS0wY2Y4N2I2NmRlZGQiLCJjdHMiOiJPQVVUSDJfU1RBVEVMRVNTX0dSQU5UIiwiYXV0aF9sZXZlbCI6MCwiYXVkaXRUcmFja2luZ0lkIjoiY2FjZmQwMmYtMGVmMy00OThjLTg1NDUtYzkzYWMxYmQ1ODg3LTI3OTAxMCIsInN1Ym5hbWUiOiJlYWE4ODRlMi0yNDM0LTQ0MmEtYTM5ZS0wY2Y4N2I2NmRlZGQiLCJpc3MiOiJodHRwczovL2FtOjQ0My9hbS9vYXV0aDIvdHNlbC9teXRlbGtvbXNlbC9saXRlIiwidG9rZW5OYW1lIjoiYWNjZXNzX3Rva2VuIiwidG9rZW5fdHlwZSI6IkJlYXJlciIsImF1dGhHcmFudElkIjoiaElkSG5xZzZqSlNRLVJ1TndsWlZsbUc5ZDRnIiwibm9uY2UiOiJ0cnVlIiwiYXVkIjoiZWY3ZTI1NDA2YWRmZmZkMjU0ZWI2MWJhZDViNTJjOTgiLCJuYmYiOjE2ODYwNDgwOTAsImdyYW50X3R5cGUiOiJhdXRob3JpemF0aW9uX2NvZGUiLCJzY29wZSI6WyJpZGVudGlmaWVyIiwicGhvbmUiLCJvcGVuaWQiLCJwcm9maWxlIl0sImF1dGhfdGltZSI6MTY4NjA0ODA4OSwicmVhbG0iOiIvdHNlbC9teXRlbGtvbXNlbC9saXRlIiwiZXhwIjoxNjg2MDQ4MTUwLCJpYXQiOjE2ODYwNDgwOTAsImV4cGlyZXNfaW4iOjYwLCJqdGkiOiJudUxTcVpnQ2xXcjRYdUFndFFaeUcySWZ5dEkifQ.ZBQ-f1d4fnz9ApyJS3vh8m8ABMXPVwKkl-UfOEU_O_wfHpTzWtSvvjsyTRcUXDDcJSrBeSwDeTgXlwurPfQ4-2D5FK0gb4y73DIYGPK_P4lhhrBDJkt4PpOErEonuRmev8s0qY7leo7AtvXHsnsESlKgw1UnbqfFLklM0zJ91yC48ycMD5MDDnEyqg_iaNvVn9Xn545tfHcMwsMkG1GYGo_8nXbHwxuRePMPwzX4PLmBqTGBxbChgm0idOQpJ1xFK5oiRIEx4g9x-rPCCp5IoaIUxhKPrtDCqmU7KfauqY2BMNUMKmYt5FgwbDh8rp2viVoots64XZHzjLFZm5p2fw"*/
        )
    }

}