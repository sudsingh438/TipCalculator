package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var etAmount : EditText
    private lateinit var tipPercent: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var tipAmount: TextView
    private lateinit var totalAmount: TextView

    private var tipPercentValue: Int = 0
    private var billAmount: Double = 0.0
    private var tipAmountValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etAmount = findViewById(R.id.editTextBillAmt)
        tipPercent = findViewById(R.id.tvTipPercent)
        seekBar = findViewById(R.id.seekBar)
        tipAmount = findViewById(R.id.tvTipAmount)
        totalAmount = findViewById(R.id.tvTotalAmount)

        seekBar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tipPercent.setText("$progress %")
                tipPercentValue = progress
                computeTipAndTotal()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        seekBar.progress = 15

        etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                computeTipAndTotal()
            }
        })
    }

    fun computeTipAndTotal() {
        var billAmountStr = etAmount.text.toString()
        if (billAmountStr.isEmpty()) {
            tipAmountValue = 0.0
            tipAmount.setText("")
            totalAmount.setText("")
            return
        }

        billAmount = billAmountStr.toDouble()
        tipAmountValue = (tipPercentValue * billAmount)/ 100
        tipAmount.setText(tipAmountValue.toString() )
        totalAmount.setText((tipAmountValue + billAmount).toString())
    }
}