package com.suitmedia.suitmediaapp.view.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.suitmediaapp.databinding.ActivityMainBinding
import com.suitmedia.suitmediaapp.view.second.SecondActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun setupAction() {
        binding.btnCheck.setOnClickListener {
            val inputPalindrome = binding.edPalindrome.text.toString()
            val isPalindrome = checkPalindrome(inputPalindrome)
            val toastMsg = if (isPalindrome) "IsPalindrome" else "not palindrome"

            AlertDialog.Builder(this).apply {
                setTitle("Check Palindrome")
                setMessage(toastMsg)
                setPositiveButton("OK") { _, _ ->
                }
                create()
                show()
            }
        }

        binding.btnNext.setOnClickListener {
            val inputName = binding.edName.text.toString()
            val intentToSecond = Intent(this, SecondActivity::class.java)
            intentToSecond.putExtra(SecondActivity.NAME, inputName)
            startActivity(intentToSecond)
        }
    }

    private fun checkPalindrome(text: String): Boolean {
        val lowerText = text.lowercase()
        val textLength = lowerText.length
        for (i in 0 until textLength / 2) {
            if (lowerText[i] != lowerText[textLength - i - 1]) return false
        }
        return true
    }
}