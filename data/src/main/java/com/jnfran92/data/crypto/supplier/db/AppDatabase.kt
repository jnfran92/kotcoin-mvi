package com.jnfran92.data.crypto.supplier.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.supplier.crypto.local.CryptoDao

@Database(entities = [Crypto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}