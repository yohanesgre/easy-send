package com.example.easysend.features.login.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.easysend.di.UserCache
import com.example.easysend.features.login.data.datasource.LoginRemoteDataSource
import com.example.easysend.network.api.Result
import com.example.easysend.network.api.ResultResponse
import com.example.easysend.network.response.login.LoginResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val userCache: UserCache
) {
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private var loginResponseLiveData = MutableLiveData<Result<ResultResponse<LoginResponse>>>()

    /*fun getLoginResult(nohp:String, password:String) =
        liveData(Dispatchers.IO) {
            emit(remoteDataSource.login(nohp, password))
    }
*/
    fun getLoginResult(nohp:String, password:String):LiveData<Result<ResultResponse<LoginResponse>>> {
        coroutineScope.launch {
            val result = remoteDataSource.login(nohp, password)
            withContext(Dispatchers.Main) {
                try {
                    loginResponseLiveData.value = result
                }catch (e: HttpException) {
                } catch (e: Throwable) {
                }
            }
        }
        return loginResponseLiveData
    }
}