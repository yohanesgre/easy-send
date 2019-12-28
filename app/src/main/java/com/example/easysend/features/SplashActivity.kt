package com.example.easysend.features

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.easysend.R
import com.example.easysend.databinding.ActivitySplashBinding
import com.example.easysend.di.Injectable
import com.example.easysend.features.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity(), Injectable {

    /*@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_splash
            )
        Handler().postDelayed({
           startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, 3000L)
    }
}