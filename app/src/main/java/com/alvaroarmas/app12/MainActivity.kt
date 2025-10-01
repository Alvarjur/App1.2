package com.alvaroarmas.app12

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    companion object {
        val list_hof = ArrayList<Record>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Calculating random number
        var randomNumber : Int = (Math.random() * 100 + 1).toInt()
        var tries = 1

        Log.d("NUMBER", randomNumber.toString())
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button_guess)
            .setOnClickListener {
                val txt_guess = findViewById<EditText>(R.id.guess_txt)
                if (txt_guess.text.isNotEmpty()) {
                    val guess = findViewById<EditText>(R.id.guess_txt).text.toString().toInt()

                    if (guess == randomNumber) {
                        val toast = Toast.makeText(
                            this,
                            "Correct!\nTries: $tries",
                            Toast.LENGTH_SHORT
                        ) // in Activity
                        toast.show()
                        val input = EditText(this)
                        input.hint = "Insert your name here"


                        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                        builder
                            .setMessage("Would you like to add your score to the HOF?")
                            .setTitle("Congratulations!")
                            .setView(input)
                            .setPositiveButton("Yes") { dialog, which ->
                                // Do something.
                                val texto = input.text.toString()
                                val intent = Intent(this, HallOfFameActivity::class.java)
                                startActivity(intent)
                                list_hof.add(Record(texto, tries.toInt()))
                                tries = 1
                            }
                            .setNegativeButton("No") { dialog, which ->
                                // Do something else.
                                dialog.dismiss()
                                tries = 1
                            }

                        val dialog: AlertDialog = builder.create()
                        dialog.show()


                        randomNumber = (Math.random() * 100 + 1).toInt()
                        Log.d("NUMBER", randomNumber.toString())

                    } else {
                        if (guess < randomNumber) {
                            val toast = Toast.makeText(
                                this,
                                "Correct number is higher!",
                                Toast.LENGTH_SHORT
                            ) // in Activity
                            toast.show()
                        } else {
                            val toast = Toast.makeText(
                                this,
                                "Correct number is lower!",
                                Toast.LENGTH_SHORT
                            ) // in Activity
                            toast.show()
                        }
                        tries += 1

                    }
                    txt_guess.setText("")
                }
            }
    }
}

class Record(val name: String, val tries: Int) {
    var intentos: Int = tries
}