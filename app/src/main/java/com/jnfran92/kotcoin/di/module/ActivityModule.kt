package com.jnfran92.kotcoin.di.module

import android.app.Activity
import com.jnfran92.kotcoin.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity){

    @Provides
    @PerActivity
    fun activity(): Activity{
        return this.activity
    }
}