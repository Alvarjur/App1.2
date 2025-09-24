package com.alvaroarmas.app12

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Calculating random number
        val randomNumber : Int = (Math.random() * 100).toInt()
        Log.d("NUMBER", randomNumber.toString())
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var tries = 0

        findViewById<Button>(R.id.button_guess)
            .setOnClickListener {
                val guess = findViewById<EditText>(R.id.guess_txt).text.toString().toInt()
                if(guess == randomNumber) {
                    val toast = Toast.makeText(this, "Correct!\nTries: $tries", Toast.LENGTH_SHORT) // in Activity
                    toast.show()
                } else {
                    if (guess < randomNumber) {
                        val toast = Toast.makeText(this, "Correct number is higher!", Toast.LENGTH_SHORT) // in Activity
                        toast.show()
                    } else {
                        val toast = Toast.makeText(this, "Correct number is lower!", Toast.LENGTH_SHORT) // in Activity
                        toast.show()
                    }
                    tries += 1
                }
            }
    }
}