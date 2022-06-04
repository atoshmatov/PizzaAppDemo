package uz.gita.pizzaappdemo.mobdev20.di.home

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.pizzaappdemo.mobdev20.domain.repository.home.HomeRepository
import uz.gita.pizzaappdemo.mobdev20.domain.repository.home.impl.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeRepositoryModule {

    @[Binds Singleton]
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}