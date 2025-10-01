package com.alvaroarmas.app12

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class HallOfFameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hall_of_fame)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txt_fame = findViewById<TextView>(R.id.txt_fame)
        var hofText = ""
        MainActivity.list_hof.forEach { record ->
            hofText += record.name + " guessed it in " + record.intentos + " tries.\n"
        }
        txt_fame.text = hofText
    }

    fun goBack(view: View) {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}