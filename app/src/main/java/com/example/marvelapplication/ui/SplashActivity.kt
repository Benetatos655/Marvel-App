package com.example.marvelapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import com.example.marvelapplication.R
import com.example.marvelapplication.utils.Constants.SPLASH_INTENT_TAG
import com.example.marvelapplication.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.marvelSeriesResponse.observe(this, {

            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putParcelableArrayListExtra(SPLASH_INTENT_TAG,ArrayList<Parcelable>(it))
            startActivity(mainIntent)
        })
    }
}