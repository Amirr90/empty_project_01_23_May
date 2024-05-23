package com.utils.di


import com.login.api.Auth
import com.login.api.LoginWithEmailAndPassword
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
    abstract fun bindsUploadImage(uploadRef: LoginWithEmailAndPassword): Auth

    @Binds
    @Singleton
    abstract fun bindsResponseHandlerRepo(auth: ResponseHandlerImpl): ResponseHandler


    @Binds
    @Singleton
    abstract fun bindSaveUserDatabaseRepo(userRef: SaveUserToRoomDatabase): UserRepo


}