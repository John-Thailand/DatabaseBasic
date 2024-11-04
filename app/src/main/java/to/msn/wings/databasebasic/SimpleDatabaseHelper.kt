package to.msn.wings.databasebasic

import android.content.Context
// import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SimpleDatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DBNAME, null, VERSION) {
    companion object {
        private const val DBNAME = "sample.sqlite"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL("CREATE TABLE books ( " +
                    "isbn TEXT PRIMARY KEY, title TEXT, price INTEGER)")
            it.execSQL("INSERT INTO books(isbn, title, price)" +
            " VALUES('978-4-7980-4512-2', 'はじめてのASP.NET', 3000)")
            it.execSQL("INSERT INTO books(isbn, title, price)" +
                    " VALUES('978-4-7980-4179-7', 'ASP.NET Core実践プログラミング', 3500)")
            it.execSQL("INSERT INTO books(isbn, title, price)" +
                    " VALUES('978-4-7741-8030-4', 'Javaポケットリファレンス ', 2680)")
            it.execSQL("INSERT INTO books(isbn, title, price)" +
                    " VALUES('978-4-7741-9617-6', 'Swiftポケットリファレンス', 2880)")
            it.execSQL("INSERT INTO books(isbn, title, price)" +
                    " VALUES('978-4-7981-3547-2', '独習PHP', 3200)")
        }
    }

    /*
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            it.execSQL(
                "CREATE TABLE books (" +
                        "isbn TEXT PRIMARY KEY, title TEXT, price INTEGER)"
            )
            val data = listOf(
                mapOf("isbn" to "978-4-7980-4512-2",
                    "title" to "はじめてのASP.NET", "price" to "3000"),
                mapOf("isbn" to "978-4-7980-4179-7",
                    "title" to "ASP.NET Core実践プログラミング", "price" to "3500"),
                mapOf("isbn" to "978-4-7741-8030-4",
                    "title" to "Javaポケットリファレンス", "price" to "2680"),
                mapOf("isbn" to "978-4-7741-9617-6",
                    "title" to "Swiftポケットリファレンス", "price" to "2780"),
                mapOf("isbn" to "978-4-7981-3547-2",
                    "title" to "独習PHP", "price" to "3200")
            )
            it.beginTransaction()
            try {
                val sql = it.compileStatement(
                    "INSERT INTO books(isbn, title, price) VALUES(?, ?, ?)"
                )
                data.forEach {
                    sql.bindString(1, it["isbn"])
                    sql.bindString(2, it["title"])
                    sql.bindString(3, it["price"])
                    sql.executeInsert()
                }
                it.setTransactionSuccessful()
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                it.endTransaction()
            }
        }
    }
    */

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let {
            it.execSQL("DROP TABLE IF EXISTS books")
            onCreate(it)
        }
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }
}