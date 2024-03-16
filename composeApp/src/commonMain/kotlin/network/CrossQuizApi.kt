package network

import de.jensklingenberg.ktorfit.http.GET

interface CrossQuizApi {
    @GET("people/1/")
    suspend fun getQuizList(): String
}