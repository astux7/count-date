package com.basta.counter.di

import BaseVM
import com.basta.counter.utils.UserPrefManager
import com.basta.counter.viewmodel.DateCounterVM
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CounterModule {
    val myModule = module {
        single { UserPrefManager(androidApplication()) }
        viewModel { DateCounterVM(get(), get()) }
        viewModel { BaseVM(get(), get()) }
    }
}