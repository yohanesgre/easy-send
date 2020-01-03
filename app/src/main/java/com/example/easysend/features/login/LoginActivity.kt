package com.example.easysend.features.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.easysend.R
import com.example.easysend.databinding.ActivityLoginBinding
import com.example.easysend.di.Injectable
import com.example.easysend.di.injectViewModel
import com.example.easysend.features.MainActivity
import com.example.easysend.features.login.viewmodel.InputLogin
import com.example.easysend.features.login.viewmodel.LoginViewModel
import com.example.easysend.network.api.Result
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), Injectable{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnMasuk.setOnClickListener {
            login()
        }
        subscribeUI()
    }

    private fun subscribeUI(){
        viewModel.result.observe(this){result->
            when(result.status){
                Result.Status.SUCCESS->{
                    if (result.data != null) {
                        viewModel.userCache.putNoHP(binding.inputLoginNohp.text.toString())
                        viewModel.userCache.putPassword(binding.inputEtLoginPass.text.toString())
                        viewModel.userCache.putTokenAccess(result.data.data.access_token)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
                Result.Status.ERROR->{
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }

        }
    }

    private fun login() {
        viewModel.inputLogin.postValue(
            InputLogin(binding.inputLoginNohp.text.toString(),
                binding.inputEtLoginPass.text.toString())
        )
    }
}