package com.example.easysend.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.easysend.R
import com.example.easysend.databinding.ActivitySplashBinding
import com.example.easysend.di.Injectable
import com.example.easysend.di.injectViewModel
import com.example.easysend.features.MainActivity
import com.example.easysend.features.onboarding.OnboardingActivity
import com.example.easysend.features.onboarding.PilihAkunActivity
import com.example.easysend.network.api.Result
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_splash
            )
        Handler().postDelayed({
            viewModel.authStatus.observe(this){result->
                when(result.status){
                    Result.Status.SUCCESS ->{
                        if (result.data!!.data != null){
                            val accessToken = result.data.data.access_token
                            viewModel.userCache.putTokenAccess(accessToken)
                            if (viewModel.userCache.getTokenAccess().isNotEmpty()){
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }else{
                                if (viewModel.userCache.isIsFirstTime()){
                                    startActivity(Intent(this, OnboardingActivity::class.java))
                                    finish()
                                }else{
                                    startActivity(Intent(this, PilihAkunActivity::class.java))
                                    finish()
                                }
                            }
                        }
                    }
                    Result.Status.ERROR ->{
                        if (viewModel.userCache.isIsFirstTime()){
                            startActivity(Intent(this, OnboardingActivity::class.java))
                            finish()
                        }else{
                            startActivity(Intent(this, PilihAkunActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }, 3000L)

    }
}