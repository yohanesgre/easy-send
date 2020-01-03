package com.example.easysend.di

import co.windly.ktxprefs.annotation.DefaultBoolean
import co.windly.ktxprefs.annotation.DefaultString
import co.windly.ktxprefs.annotation.Prefs

@Prefs(value = "UserCache")
class UserCacheProvider(
    @DefaultString(value = "")
    internal val tokenAccess: String,

    @DefaultString(value = "")
    internal val noHP: String,

    @DefaultString(value = "")
    internal val password: String,

    @DefaultBoolean(value = true)
    internal val isFirstTime: Boolean
)