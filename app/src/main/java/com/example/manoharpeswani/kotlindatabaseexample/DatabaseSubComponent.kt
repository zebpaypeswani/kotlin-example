package com.example.manoharpeswani.kotlindatabaseexample

import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

@Subcomponent
interface DatabaseSubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}