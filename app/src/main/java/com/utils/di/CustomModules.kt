package com.utils.di


import com.cart.repo.CartRepo
import com.cart.repo.CartRepoImpl
import com.login.api.Auth
import com.login.api.LoginWithEmailAndPassword
import com.login2.repo.Login2Repo
import com.login2.repo.Login2RepoImpl
import com.products.repo.ProductsRepo
import com.products.repo.ProductsRepoImpl
import com.utils.db.room.repo.SaveUserToRoomDatabase
import com.utils.db.room.repo.UserRepo
import com.utils.network.responseHandler.ResponseHandler
import com.utils.network.responseHandler.ResponseHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CustomModules {


    @Binds
    @Singleton
    abstract fun bindsResponseHandlerRepo(auth: ResponseHandlerImpl): ResponseHandler


    @Binds
    @Singleton
    abstract fun bindSaveUserDatabaseRepo(userRef: SaveUserToRoomDatabase): UserRepo

    @Binds
    @Singleton
    abstract fun bindLogin2Repo(userRef: Login2RepoImpl): Login2Repo

    @Binds
    @Singleton
    abstract fun bindAuth(userRef: LoginWithEmailAndPassword): Auth


    @Binds
    @Singleton
    abstract fun bindProductRepo(userRef: ProductsRepoImpl): ProductsRepo

    @Binds
    @Singleton
    abstract fun bindCartRepo(userRef: CartRepoImpl): CartRepo
}