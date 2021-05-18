package com.christine.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.christine.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val stringTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringTextField.toDoubleOrNull()
        if(cost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.options_twenty_percent -> 0.20
            R.id.options_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage

        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
    }

    private fun displayTip(tip: Double){
        val formattedTip =NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}