package sk.sksv.mvvmdemoapp.network

import sk.sksv.mvvmdemoapp.model.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getPosts() : List<Post> = apiServices.getPost()
}