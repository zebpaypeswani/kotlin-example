package com.example.manoharpeswani.kotlindatabaseexample

import dagger.Component

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

@Component(modules = [(AppModule::class), (DatabaseValueModule::class)])
interface AppComponent {

    fun inject(application: MainApplication)
}