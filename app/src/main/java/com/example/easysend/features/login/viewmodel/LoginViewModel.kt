package com.example.easysend.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.example.easysend.di.UserCache
import com.example.easysend.features.login.data.repo.LoginRepository
import com.example.easysend.network.api.Result
import com.example.easysend.network.api.ResultResponse
import com.example.easysend.network.response.login.LoginResponse
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    val userCache: UserCache
) : ViewModel() {
    val inputLogin = MutableLiveData<InputLogin>()

    val result: LiveData<Result<ResultResponse<LoginResponse>>> = switchMap(inputLogin){ input->
        if (input.noHP.isNotEmpty()){
            inputLogin.value.let {
                repository.getLoginResult(input.noHP, input.password)
            }
        }else{
            MutableLiveData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.completableJob.cancel()
    }
}

data class InputLogin(
    val noHP:String,
    val password:String
)