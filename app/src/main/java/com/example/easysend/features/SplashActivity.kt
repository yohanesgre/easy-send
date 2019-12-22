package com.example.easysend.features

import androidx.appcompat.app.AppCompatActivity
import com.example.easysend.di.Injectable

class SplashActivity : AppCompatActivity(), Injectable {

    /*@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel*/
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        val binding: ActivityAuthSplashBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_auth_splash
            )
        viewModel.getProfile.observe(this, Observer {
            Handler().postDelayed({
                if (it != null) {
                    println("UserID: ${it.id}")
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("UserID", it.id)
                    }
                    startActivity(intent)
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }, 2000L)
        })
    }*/
}