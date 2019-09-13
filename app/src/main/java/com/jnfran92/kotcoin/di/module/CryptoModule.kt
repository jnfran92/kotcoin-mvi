package com.jnfran92.kotcoin.di.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jnfran92.kotcoin.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class CryptoModule {

    @Provides
    @PerActivity
    fun layoutManager(context: Context): RecyclerView.LayoutManager{
        return LinearLayoutManager(context)
    }
}