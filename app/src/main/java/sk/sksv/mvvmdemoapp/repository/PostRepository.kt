package sk.sksv.mvvmdemoapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import sk.sksv.mvvmdemoapp.model.Post
import sk.sksv.mvvmdemoapp.network.ApiServiceImpl
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getPosts(): Flow<List<Post>> {
        return flow {
            emit(apiServiceImpl.getPosts())
        }.flowOn(Dispatchers.IO)
    }
}