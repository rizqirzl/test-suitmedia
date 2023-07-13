package com.suitmedia.suitmediaapp.view.second

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.databinding.ActivitySecondBinding
import com.suitmedia.suitmediaapp.view.third.ThirdActivity


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupData()
        setupAction()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.custom_actionbar)
            setBackgroundDrawable(ContextCompat.getDrawable(this@SecondActivity, R.color.white))
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayHomeAsUpEnabled(true)
        }

        val actionBarTitle: TextView = findViewById(R.id.tvTitle)
        actionBarTitle.setText(R.string.second_screen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.WHITE
        }
    }

    private fun setupData() {
        val name = intent.getStringExtra(NAME)
        binding.tvName.text = name
    }

    private fun setupAction() {
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == ThirdActivity.RESULT_CODE && result.data != null) {
                val selectedValue = result.data?.getStringExtra(ThirdActivity.SELECTED_USERNAME)
                if (selectedValue != null) {
                    binding.tvSelectedUsername.text = selectedValue
                }
            }
        }

        binding.btnChoose.setOnClickListener {
            val intentToThird = Intent(this, ThirdActivity::class.java)
            resultLauncher.launch(intentToThird)
        }
    }

    companion object {
        const val NAME = "name"
    }
}