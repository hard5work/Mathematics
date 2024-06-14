package com.xdroid.app.mathematics.di.module

import com.xdroid.app.mathematics.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}