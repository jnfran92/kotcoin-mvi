package com.jnfran92.kotcoin.di.component

import android.app.Activity
import com.jnfran92.kotcoin.di.PerActivity
import com.jnfran92.kotcoin.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules =[ActivityModule::class])
interface ActivityComponent {

    // exposed to sub-graphs
    fun activity(): Activity
}