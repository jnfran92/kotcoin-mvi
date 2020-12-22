package com.jnfran92.kotcoin.di.module

import android.content.Context
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    @FragmentScoped
    fun layoutManager(@ApplicationContext context: Context): RecyclerView.LayoutManager{

        val orientation = context.resources.configuration.orientation
        var layoutManager = GridLayoutManager(context, 1)

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = GridLayoutManager(context, 2)
        }
        return layoutManager
    }
}