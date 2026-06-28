package com.example.astrocalci

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.astrocalci.R
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Master Constants
        val etConstantDivide = findViewById<EditText>(R.id.etConstantDivide)
        val etConstantAddition = findViewById<EditText>(R.id.etConstantAddition)
        val etConstantMultiply = findViewById<EditText>(R.id.etConstantMultiply)
        val btnCalculateAll = findViewById<Button>(R.id.btnCalculateAll)

        // Grand Total View
        val tvGrandTotal = findViewById<TextView>(R.id.tvGrandTotal)

        // Grouping the 24 input/output pairs into a list
        val computationRows = listOf(
            Pair(findViewById<EditText>(R.id.etDegree1), findViewById<TextView>(R.id.tvFinal1)),
            Pair(findViewById<EditText>(R.id.etDegree2), findViewById<TextView>(R.id.tvFinal2)),
            Pair(findViewById<EditText>(R.id.etDegree3), findViewById<TextView>(R.id.tvFinal3)),
            Pair(findViewById<EditText>(R.id.etDegree4), findViewById<TextView>(R.id.tvFinal4)),
            Pair(findViewById<EditText>(R.id.etDegree5), findViewById<TextView>(R.id.tvFinal5)),
            Pair(findViewById<EditText>(R.id.etDegree6), findViewById<TextView>(R.id.tvFinal6)),
            Pair(findViewById<EditText>(R.id.etDegree7), findViewById<TextView>(R.id.tvFinal7)),
            Pair(findViewById<EditText>(R.id.etDegree8), findViewById<TextView>(R.id.tvFinal8)),
            Pair(findViewById<EditText>(R.id.etDegree9), findViewById<TextView>(R.id.tvFinal9)),
            Pair(findViewById<EditText>(R.id.etDegree10), findViewById<TextView>(R.id.tvFinal10)),
            Pair(findViewById<EditText>(R.id.etDegree11), findViewById<TextView>(R.id.tvFinal11)),
            Pair(findViewById<EditText>(R.id.etDegree12), findViewById<TextView>(R.id.tvFinal12)),
            Pair(findViewById<EditText>(R.id.etDegree13), findViewById<TextView>(R.id.tvFinal13)),
            Pair(findViewById<EditText>(R.id.etDegree14), findViewById<TextView>(R.id.tvFinal14)),
            Pair(findViewById<EditText>(R.id.etDegree15), findViewById<TextView>(R.id.tvFinal15)),
            Pair(findViewById<EditText>(R.id.etDegree16), findViewById<TextView>(R.id.tvFinal16)),
            Pair(findViewById<EditText>(R.id.etDegree17), findViewById<TextView>(R.id.tvFinal17)),
            Pair(findViewById<EditText>(R.id.etDegree18), findViewById<TextView>(R.id.tvFinal18)),
            Pair(findViewById<EditText>(R.id.etDegree19), findViewById<TextView>(R.id.tvFinal19)),
            Pair(findViewById<EditText>(R.id.etDegree20), findViewById<TextView>(R.id.tvFinal20)),
            Pair(findViewById<EditText>(R.id.etDegree21), findViewById<TextView>(R.id.tvFinal21)),
            Pair(findViewById<EditText>(R.id.etDegree22), findViewById<TextView>(R.id.tvFinal22)),
            Pair(findViewById<EditText>(R.id.etDegree23), findViewById<TextView>(R.id.tvFinal23)),
            Pair(findViewById<EditText>(R.id.etDegree24), findViewById<TextView>(R.id.tvFinal24))
        )

        btnCalculateAll.setOnClickListener {
            val divStr = etConstantDivide.text.toString()
            val addStr = etConstantAddition.text.toString()
            val multStr = etConstantMultiply.text.toString()

            if (divStr.isEmpty() || addStr.isEmpty() || multStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all 3 constant values at the top", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val divideConst = divStr.toDouble()
            val additionConst = addStr.toDouble()
            val multiplyConst = multStr.toDouble()

            // Variable to keep track of the cumulative sum
            var grandTotal = 0.0

            // Run through all 12 rows dynamically
            for (row in computationRows) {
                val etDegree = row.first
                val tvFinal = row.second
                val degreeStr = etDegree.text.toString()

                if (degreeStr.isNotEmpty()) {
                    val degree = degreeStr.toDouble()
                    if (degree != 0.0) {
                        // Formula Operations
                        val rawDivide = divideConst / degree
                        val roundedDivide = BigDecimal(rawDivide).setScale(2, RoundingMode.HALF_UP).toDouble()
                        val finalResult = (roundedDivide + additionConst) * multiplyConst

                        tvFinal.text = String.format("Final: %.2f", finalResult)

                        // Add this row's final result to our grand total tracker
                        grandTotal += finalResult
                    } else {
                        tvFinal.text = "Error: 0"
                    }
                } else {
                    tvFinal.text = "Final: —"
                }
            }

            // After running through all rows, update the Grand Total text box
            tvGrandTotal.text = String.format("%.2f", grandTotal)
        }
    }
}