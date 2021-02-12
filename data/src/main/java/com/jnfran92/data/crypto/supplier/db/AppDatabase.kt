package com.jnfran92.data.crypto.supplier.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.UsdPriceLocal
import com.jnfran92.data.crypto.supplier.crypto.local.CryptoDao

@Database(
    entities = [
        CryptoLocal::class,
        UsdPriceLocal::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}