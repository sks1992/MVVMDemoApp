package sk.sksv.mvvmdemoapp.network

import retrofit2.http.GET
import sk.sksv.mvvmdemoapp.model.Post

interface ApiServices {
    @GET("posts")
    suspend fun getPost() :List<Post>
}