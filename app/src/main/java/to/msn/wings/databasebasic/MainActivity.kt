package to.msn.wings.databasebasic

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ヘルパーを準備
        val helper = SimpleDatabaseHelper(this)
        val txtIsbn = findViewById<EditText>(R.id.txtIsbn)
        val txtTitle = findViewById<EditText>(R.id.txtTitle)
        val txtPrice = findViewById<EditText>(R.id.txtPrice)

        // 保存ボタンを押下した時に呼び出されるコード
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            helper.writableDatabase.use { db ->
                val cv = ContentValues().apply {
                    put("isbn", txtIsbn.text.toString())
                    put("title", txtTitle.text.toString())
                    put("price", txtPrice.text.toString())
                }
                db.insert("books", null, cv)
                Toast.makeText(
                    this,
                    "データの登録に成功しました。",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 削除ボタンを押した時に呼び出されるコード
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            helper.writableDatabase.use { db ->
                val params = arrayOf(txtIsbn.text.toString())
                db.delete("books", "isbn = ?", params)
                Toast.makeText(
                    this,
                    "データの削除に成功しました。",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 検索ボタンを押した時に呼び出されるコード
        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            val cols = arrayOf("isbn", "title", "price")
            val params = arrayOf(txtIsbn.text.toString())
            helper.readableDatabase.use { db ->
                db.query(
                    "books", cols, "isbn = ?",
                    params, null, null, null, null
                ).use { cs ->
                    if (cs.moveToFirst()) {
                        txtTitle.setText(cs.getString(1))
                        txtPrice.setText(cs.getString(2))
                    } else {
                        Toast.makeText(
                            this,
                            "データがありません。",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}