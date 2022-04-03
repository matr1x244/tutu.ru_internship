package com.geekbrains.tuturuinternship

import com.geekbrains.tuturuinternship.details.DetailViewModel
import com.geekbrains.tuturuinternship.main.ScreenViewModel
import com.geekbrains.tuturuinternship.repository.Repository
import com.geekbrains.tuturuinternship.repository.RepositoryIpml
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Repository> {
        RepositoryIpml()
    }
    //View models
    viewModel { ScreenViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}