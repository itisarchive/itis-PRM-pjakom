package pl.edu.pja.kdudek.pja_kom.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.edu.pja.kdudek.pja_kom.data.CartRepository
import pl.edu.pja.kdudek.pja_kom.data.CartRepositoryImpl
import pl.edu.pja.kdudek.pja_kom.data.LocalProductRepository
import pl.edu.pja.kdudek.pja_kom.data.ProductRepository
import pl.edu.pja.kdudek.pja_kom.data.db.ShopDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

    @Binds
    abstract fun bindsProductRepository(
        localProductRepository: LocalProductRepository
    ): ProductRepository

    companion object {

        @Singleton
        @Provides
        fun provideShopDb(
            @ApplicationContext context: Context
        ): ShopDb {
            return Room.databaseBuilder(
                context,
                ShopDb::class.java,
                "shop.db"
            ).build()
        }
    }
}
