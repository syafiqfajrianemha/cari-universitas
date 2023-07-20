package com.emha.cariuniversitas

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emha.cariuniversitas.adapter.UniversityAdapter
import com.emha.cariuniversitas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var universityViewModel: UniversityViewModel
    private lateinit var universityAdapter: UniversityAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        universityAdapter = UniversityAdapter()
        universityAdapter.notifyDataSetChanged()

        universityViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UniversityViewModel::class.java]

        binding.rvUniversity.setHasFixedSize(true)
        binding.rvUniversity.layoutManager = LinearLayoutManager(this@MainActivity)

        binding.rvUniversity.adapter = universityAdapter

        binding.etKeyword.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUniversity()
                hideKeyboard()
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        }

        universityViewModel.getSearchUniversity().observe(this) { university ->
            if (university != null) {
                universityAdapter.setList(university)
                showLoading(false)
            }
        }
    }

    private fun searchUniversity() {
        val univ = binding.etKeyword.text.toString().trim()

        if (univ.isNotEmpty()) {
            showLoading(true)
            universityViewModel.setSearchUniversity(univ)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus

        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}