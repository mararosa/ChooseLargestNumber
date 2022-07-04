package com.estudos.chooselargestnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.estudos.chooselargestnumber.databinding.ActivityMainBinding
import java.util.*

private lateinit var binding: ActivityMainBinding
private var points = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pickRandomNumbers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
            leftButton.setOnClickListener { checkIfCorrectAnswer(true) }
            rightButton.setOnClickListener { checkIfCorrectAnswer(false) }
            resetButton.setOnClickListener { resetPoints() }
        }
    }

    private fun resetPoints() {
        binding.points.text = "Points: 0"
        points = 0
    }


    private fun checkIfCorrectAnswer(isLeft: Boolean) {
        val leftNum = binding.leftButton.text.toString().toInt()
        val rightNum = binding.rightButton.text.toString().toInt()

        if (isLeft && leftNum > rightNum || !isLeft && leftNum < rightNum) {
            points++
            Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show()
        } else {
            points--
            Toast.makeText(this, "Oh no!", Toast.LENGTH_SHORT).show()
        }
        binding.points.text = "Points: $points"
        pickRandomNumbers()
    }

    // Picks two new random numbers to put on the left/right buttons.
    private fun pickRandomNumbers() {
        val r = Random()
        val num1 = r.nextInt(10)
        var num2 = num1

        while (num1 == num2) {
            num2 = r.nextInt(10)
        }
        binding.leftButton.text = "$num1"
        binding.rightButton.text = "$num2"
    }
}