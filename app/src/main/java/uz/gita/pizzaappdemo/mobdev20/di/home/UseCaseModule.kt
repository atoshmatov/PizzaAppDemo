package uz.gita.pizzaappdemo.mobdev20.di.home

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.pizzaappdemo.mobdev20.domain.usecase.home.HomeUseCase
import uz.gita.pizzaappdemo.mobdev20.domain.usecase.home.impl.HomeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase
}