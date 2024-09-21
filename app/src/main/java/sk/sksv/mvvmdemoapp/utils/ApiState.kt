package sk.sksv.mvvmdemoapp.utils

import sk.sksv.mvvmdemoapp.model.Post

sealed class ApiState {
    object Loading : ApiState()
    object Empty : ApiState()
    class Error(val message: Throwable) : ApiState()
    class Success(val data: List<Post>) : ApiState()
}

/*
* sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T> : NetworkResult<T>()

}
* */