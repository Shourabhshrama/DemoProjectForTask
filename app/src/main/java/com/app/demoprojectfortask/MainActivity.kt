package com.app.demoprojectfortask

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.demoprojectfortask.data.remote.models.PostResponseItem
import com.app.demoprojectfortask.databinding.ActivityMainBinding
import com.app.demoprojectfortask.ui.PostViewModel
import com.app.demoprojectfortask.ui.UIState
import com.app.demoprojectfortask.ui.adapter.PostAdapter
import com.app.demoprojectfortask.utils.SOMETHING_WENT_WRONG
import com.app.demoprojectfortask.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setObserver()

    }


    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.postData.collect() { state ->

                when (state) {
                    is UIState.Success -> {

                        showSuccessState(state.data)
                    }

                    is UIState.Failure -> {
                        showErrorState(state.errorMessage)

                    }

                    is UIState.Loading -> showLoadingState()
                    else -> {
                        showToast(SOMETHING_WENT_WRONG)
                    }
                }
            }
        }
    }


    private fun showLoadingState() {
        binding.content.progress.visibility = View.VISIBLE
    }

    private fun showSuccessState(data: List<PostResponseItem>) {
        binding.content.progress.visibility = View.GONE
        var layoutManager=LinearLayoutManager(this)
        binding.content.recyclerviewPost.layoutManager = layoutManager

        var postAdapter = PostAdapter(data)
        binding.content.recyclerviewPost.adapter = postAdapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.content.recyclerviewPost.getContext(),
            layoutManager.getOrientation()
        )

        binding.content.recyclerviewPost.addItemDecoration(dividerItemDecoration)
    }

    private fun showErrorState(errorMessage: String) {
        binding.content.progress.visibility = View.GONE

        showToast(errorMessage)

    }


}