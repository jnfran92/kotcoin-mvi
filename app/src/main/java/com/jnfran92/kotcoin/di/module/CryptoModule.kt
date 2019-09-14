package com.jnfran92.kotcoin.di.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jnfran92.kotcoin.di.PerActivity
import dagger.Module
import dagger.Provides
import androidx.recyclerview.widget.GridLayoutManager



@Module
class CryptoModule {

    @Provides
    @PerActivity
    fun layoutManager(context: Context): RecyclerView.LayoutManager{
        return GridLayoutManager(context, 2)
    }
}