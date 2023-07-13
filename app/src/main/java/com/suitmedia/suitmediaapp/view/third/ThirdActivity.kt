package com.suitmedia.suitmediaapp.view.third

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.databinding.ActivityThirdBinding
import com.suitmedia.suitmediaapp.view.ViewModelFactory

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[UserViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager

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
            setBackgroundDrawable(ContextCompat.getDrawable(this@ThirdActivity, R.color.white))
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayHomeAsUpEnabled(true)
        }

        val actionBarTitle: TextView = findViewById(R.id.tvTitle)
        actionBarTitle.setText(R.string.third_screen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.WHITE
        }
    }

    private fun setupData() {
        adapter = UserListAdapter { user ->
            val userFullname = "${user.firstName} ${user.lastName}"
            val resultIntent = Intent()
            resultIntent.putExtra(SELECTED_USERNAME, userFullname)
            setResult(RESULT_CODE, resultIntent)
            finish()
        }

        binding.rvUsers.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.getUsers().observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setupAction() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    companion object {
        const val SELECTED_USERNAME = "selected_username"
        const val RESULT_CODE = 110
    }
}