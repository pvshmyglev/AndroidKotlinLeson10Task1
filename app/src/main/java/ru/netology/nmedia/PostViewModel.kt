package ru.netology.nmedia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class PostViewModel (application: Application) : AndroidViewModel(application), PostInteractionCommands{

    private val repository : PostRepository = PostRepositoryFileImpl(application)
    val data by repository::data
    private val emptyPost = Post(
        0,
        "",
        "",
        "",
        "",
        false,
        0,
        0,
        0
    )
    val editedPost = MutableLiveData(emptyPost)

    override fun onLike(post: Post) {
        repository.likeById(post.id)
    }

    override fun onShare(post: Post) {

        repository.shareById(post.id)
    }

    override fun onRemove(post: Post) {
        repository.removeById(post.id)
    }

    override fun onEditPost(post: Post) {

        editedPost.value = post

    }

    override fun onSaveContent(newContent: String) {

        val text = newContent.trim()

        editedPost.value?.let { thisEditedPost ->

            if (thisEditedPost.content != text) {

                if (thisEditedPost.id == 0) {
                    repository.saveNewPost(thisEditedPost.copy(content = text))
                } else {
                    repository.editPost(thisEditedPost.copy(content = text))
                }
            }

            editedPost.value = emptyPost

        }

    }

    override fun onCancelEdit() {
        editedPost.value = emptyPost
    }

}
