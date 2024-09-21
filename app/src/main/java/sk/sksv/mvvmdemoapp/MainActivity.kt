package sk.sksv.mvvmdemoapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sk.sksv.mvvmdemoapp.adapter.PostAdapter
import sk.sksv.mvvmdemoapp.databinding.ActivityMainBinding
import sk.sksv.mvvmdemoapp.utils.ApiState
import sk.sksv.mvvmdemoapp.view_model.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        viewModel.getPost()
        lifecycleScope.launch {
            viewModel._postStateFlow.collect { state ->
                when (state) {
                    ApiState.Empty -> {}

                    is ApiState.Error -> {
                        binding.recyclerView.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("TAG", "Error Occure :${state.message}")
                        Toast.makeText(this@MainActivity, "${state.message}", Toast.LENGTH_SHORT)
                            .show()
                    }

                    ApiState.Loading -> {
                        binding.recyclerView.isVisible = false
                        binding.progressBar.isVisible = true
                    }

                    is ApiState.Success -> {
                        binding.recyclerView.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.setData(state.data)
                    }
                }

            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        postAdapter = PostAdapter(ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}