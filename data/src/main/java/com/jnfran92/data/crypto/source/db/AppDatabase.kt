package com.jnfran92.data.crypto.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.PriceLocal
import com.jnfran92.data.crypto.source.crypto.local.CryptoDao

@Database(
    entities = [
        CryptoLocal::class,
        PriceLocal::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}