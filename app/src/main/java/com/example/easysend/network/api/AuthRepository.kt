package com.example.easysend.network.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.easysend.di.UserCache
import com.example.easysend.features.login.data.datasource.LoginRemoteDataSource
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val dataSource: LoginRemoteDataSource,
    private val userCache: UserCache
){
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val appNoHp =  userCache.getNoHP()
    private val appPassword =  userCache.getPassword()
    private val appTokenAccess =  userCache.getTokenAccess()

    fun checkAuthTokenAccess(): LiveData<Boolean> {
        var token: String?
        val status = MutableLiveData<Boolean>()
        coroutineScope.launch {
            val auth = dataSource.login(appNoHp, appPassword)
            withContext(Dispatchers.Main) {
                try {
                    when(auth.status){
                        Result.Status.SUCCESS->{
                            if (auth.data!!.data.access_token.isNotEmpty()){
                                token = auth.data.data.access_token
                                status.value =
                                    if (token != appTokenAccess){
                                        userCache.putTokenAccess(token)
                                        true
                                    }else{
                                        true
                                    }
                            }
                        }
                        Result.Status.ERROR->{
                            status.value = false
                        }
                    }
                }catch (e: HttpException) {
                    status.value = false
                }catch (e: Throwable) {
                    status.value = false
                }
            }
        }
        return status
    }

    fun refreshAccessToken(newToken:String):String{
        userCache.putTokenAccess(newToken)
        return appTokenAccess
    }
}