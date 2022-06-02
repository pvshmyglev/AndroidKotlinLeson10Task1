package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter =  PostAdapter(viewModel)

        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->

            result ?: return@registerForActivityResult
            viewModel.onSaveContent(result)

        }

        binding.listOfPosts.adapter = adapter

        viewModel.data.observe(this) { postsList ->

            adapter.submitList(postsList)

        }

        binding.fabNewPost.setOnClickListener {

            viewModel.onCancelEdit()
            editPostLauncher.launch(viewModel.editedPost.value)

        }

        viewModel.editedPost.observe(this) { post ->

            if (post.id != 0) editPostLauncher.launch(post)

        }

    }

}