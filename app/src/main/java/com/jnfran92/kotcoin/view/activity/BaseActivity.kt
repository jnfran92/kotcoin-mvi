package com.jnfran92.kotcoin.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.di.component.ApplicationComponent
import com.jnfran92.kotcoin.di.module.ActivityModule

/**
 * BaseActivity for managing Injection components and default UI elements: Fragments
 */
abstract class BaseActivity: AppCompatActivity() {

    lateinit var applicationComponent: ApplicationComponent
    lateinit var activityModule: ActivityModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initApplicationComponent()
        this.initActivityModule()
    }

    /**
     * Getting appComponent from [KotcoinApp]
     */
    private fun initApplicationComponent(){
        this.applicationComponent = (application as KotcoinApp).applicationComponent
    }

    /**
     * Init Activity Module for Injection
     */
    private fun initActivityModule(){
        this.activityModule = ActivityModule(this)
    }

}