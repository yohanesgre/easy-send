package com.example.easysend.di

import android.content.Context

class UserCache constructor(context: Context) :
    UserCacheProviderPrefs(context.requireUserCacheProvider())