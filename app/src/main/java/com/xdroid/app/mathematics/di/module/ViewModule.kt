package com.xdroid.app.mathematics.di.module

import com.xdroid.app.mathematics.utils.vm.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
}