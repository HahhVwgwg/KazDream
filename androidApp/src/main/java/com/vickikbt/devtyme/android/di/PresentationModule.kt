package com.vickikbt.devtyme.android.di

import com.vickikbt.devtyme.android.ui.activity.MainActivityViewModel
import com.vickikbt.devtyme.android.ui.screens.home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { MainActivityViewModel(authRepository = get()) }
    viewModel {
        HomeViewModel(
            authRepository = get(),
            dateTimeRepository = get(),
            citiesRepository = get()
        )
    }
}
