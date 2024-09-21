package sk.sksv.mvvmdemoapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import sk.sksv.mvvmdemoapp.repository.PostRepository
import sk.sksv.mvvmdemoapp.utils.ApiState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow: StateFlow<ApiState> = postStateFlow

    fun getPost() {
        viewModelScope.launch {
            postStateFlow.value = ApiState.Loading
            postRepository.getPosts().catch { e ->
                postStateFlow.value = ApiState.Error(e)
            }.collect { data ->
                postStateFlow.value = ApiState.Success(data)
            }
        }
    }
}