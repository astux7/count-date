package com.astux7.counter.di

import com.astux7.counter.utils.UserPrefManager
import com.astux7.counter.viewmodel.DateCounterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CounterModule {
    val myModule = module {
        single { UserPrefManager(androidApplication()) }
        viewModel { DateCounterViewModel(get()) }
    }
}