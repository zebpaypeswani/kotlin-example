package com.example.manoharpeswani.kotlindatabaseexample

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

@Module(subcomponents = [(DatabaseSubComponent::class)])
abstract class DatabaseValueModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindsToDoActivityInjectorFactory(builder: DatabaseSubComponent.Builder): AndroidInjector.Factory<out Activity>
}