package com.apolis.myapplication.ui

import com.apolis.myapplication.api.ApiClient
import com.apolis.myapplication.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoChars {
    val endpoint = ApiClient.getEndpoint()

    suspend fun getRMCharacters():List<Result>?{
        return withContext(Dispatchers.IO){
            val call = endpoint.getCharacters()
            val response = call.execute()
            if(response.code() in 200..399){
                response.body()!!.results
            }else{
                null
            }
        }
    }
}