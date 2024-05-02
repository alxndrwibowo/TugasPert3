package com.example.tugaspert3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val pilihan = arrayOf("Pilih kategori", "Anak-anak", "Dewasa")
    var posisi = 0

    //deklarasi dan relasi terhadap id layout (val)
    //primitif = int, double, float
    //obj = string, array, dst

    lateinit var editBerat: EditText
    lateinit var editTinggi: EditText
    lateinit var btnHitung: Button
    lateinit var txtHasil: TextView
    lateinit var spinner: Spinner

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editBerat = findViewById(R.id.numberWeight)
        editTinggi = findViewById(R.id.numberHeight)
        btnHitung = findViewById(R.id.btnCalculate)
        txtHasil = findViewById(R.id.status)
        spinner = findViewById(R.id.kategori)

        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pilihan)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "kategori terpilih : " + pilihan[position],
                    Toast.LENGTH_SHORT
                ).show()
                posisi = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //set event listener
        btnHitung.setOnClickListener {
            val berat = editBerat.text.toString().toDouble()
            val tinggi = editTinggi.text.toString().toDouble()

            //convert ke float/ double
            val bmi = berat.toFloat() / ((tinggi.toFloat()/100) * (tinggi.toFloat()/100))

            txtHasil.text = bmi.toString()

            when (posisi) {
                0 -> {
                    txtHasil.text = "Jangan lupa pilih kategori"
                }
                1 -> {
                    resultBMIAnak(bmi)
                }
                2 -> {
                    resultBMIDewasa(bmi)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun resultBMIAnak(bmi: Float) {
        txtHasil.text = bmi.toString()
        when {
            bmi < 5 -> {
                txtHasil.text = "Underweight"
            }

            bmi in 5.0..84.9 -> {
                txtHasil.text = "Normal"
            }

            bmi in 85.0 .. 95.0 -> {
                txtHasil.text = "Overweight"
            }

            bmi > 95 -> {
                txtHasil.text = "Obese"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun resultBMIDewasa(bmi: Float) {
        txtHasil.text = bmi.toString()
        when {
            bmi < 18.5 -> {
                txtHasil.text = "Underweight"
            }

            bmi in 18.5..24.9 -> {
                txtHasil.text = "Normal"
            }

            bmi in 25.0 .. 30.0 -> {
                txtHasil.text = "Overweight"
            }

            bmi > 30 -> {
                txtHasil.text = "Obese"
            }
        }
    }
}