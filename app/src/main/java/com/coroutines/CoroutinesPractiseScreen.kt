package com.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emptyprojectt1.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoroutinesPractiseScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_practise_screen)
    }
}