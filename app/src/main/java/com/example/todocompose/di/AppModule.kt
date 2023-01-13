package com.example.todocompose.di

import android.app.Application
import androidx.room.Room
import com.example.todocompose.data.ToDoDatabase
import com.example.todocompose.data.ToDoRepository
import com.example.todocompose.data.ToDoRepositoryImpl
import com.example.todocompose.data.repository.RTShopListRepositoryImpl
import com.example.todocompose.domain.repository.RTShopListRepository
import com.example.todocompose.shopData.ShopDatabase
import com.example.todocompose.shopData.ShoppingListRepo
import com.example.todocompose.shopData.ShoppingListRepoImpl
import com.example.todocompose.util.Constants.RTShop
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun providesToDoDatabase(app:Application):ToDoDatabase{
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoRepository(db:ToDoDatabase):ToDoRepository{
        return ToDoRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun providesShopDatabase(app:Application):ShopDatabase{
        return Room.databaseBuilder(
            app,
            ShopDatabase::class.java,
            "shop_db"
        ).build()

    }

    @Provides
    @Singleton
    fun providesShopRepo(db:ShopDatabase):ShoppingListRepo{
        return ShoppingListRepoImpl(db.dao)
    }

}