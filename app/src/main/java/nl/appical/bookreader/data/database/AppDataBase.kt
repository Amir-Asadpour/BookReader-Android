package nl.appical.bookreader.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.appical.bookreader.data.database.dao.BookDao
import nl.appical.bookreader.data.models.DbBook

@Database(entities = [DbBook::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}