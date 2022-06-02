package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityEditPostBinding

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textNewPost.requestFocus()

        binding.textNewPost.setText(intent.getStringExtra(Intent.EXTRA_TEXT))

        binding.fabOk.setOnClickListener {

            val intent = Intent()

            if (binding.textNewPost.text.isBlank()) {

                setResult(Activity.RESULT_CANCELED, intent)

            } else {

                val content = binding.textNewPost.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)

                setResult(Activity.RESULT_OK, intent)


            }

            finish()

        }

    }
}