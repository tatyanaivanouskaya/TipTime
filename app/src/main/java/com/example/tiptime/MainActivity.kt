package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculateButton: Button = binding.calculateButton
        calculateButton.setOnClickListener { calculateTip() }
    }


    private fun calculateTip() {
        var stringInTextField = binding.costOfService.text.toString()
        var cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        var tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.good_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        var formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = formattedTip
    }
}
