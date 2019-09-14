package com.jnfran92.kotcoin.di.module

import android.content.Context
import android.content.res.Configuration
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

        val orientation = context.resources.configuration.orientation
        var layoutManager = GridLayoutManager(context, 1)

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = GridLayoutManager(context, 2)
        }
        return layoutManager
    }
}