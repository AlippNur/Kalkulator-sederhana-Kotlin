package com.example.praktikum3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtNumber1: EditText
    private lateinit var edtNumber2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber1 = findViewById(R.id.edtNumber1)
        edtNumber2 = findViewById(R.id.edtNumber2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        tvResult = findViewById(R.id.tvResult)

        btnAdd.setOnClickListener(this)
        btnSubtract.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivide.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onClick(view: View?) {
        val input1 = edtNumber1.text.toString().trim()
        val input2 = edtNumber2.text.toString().trim()

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Masukkan kedua angka!", Toast.LENGTH_SHORT).show()
            return
        }

        val number1 = input1.toDouble()
        val number2 = input2.toDouble()
        var result = 0.0

        when (view?.id) {
            R.id.btnAdd -> result = number1 + number2
            R.id.btnSubtract -> result = number1 - number2
            R.id.btnMultiply -> result = number1 * number2
            R.id.btnDivide -> {
                if (number2 == 0.0) {
                    Toast.makeText(this, "Tidak bisa dibagi dengan nol!", Toast.LENGTH_SHORT).show()
                    return
                }
                result = number1 / number2
            }
        }
        tvResult.text = "Hasil: $result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}