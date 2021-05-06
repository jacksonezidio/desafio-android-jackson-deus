package com.example.marvel.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.data.model.Results
import com.example.marvel.ui.main.intent.MainIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.marvel.R
import com.example.marvel.data.api.ApiHelperImpl
import com.example.marvel.data.api.RetrofitBuilder
import com.example.marvel.data.model.MarvelResponse
import com.example.marvel.ui.main.adapter.MainAdapter
import com.example.marvel.ui.main.viewmodel.MainViewModel
import com.example.marvel.ui.main.viewstate.MainState
import com.example.marvel.util.ViewModelFactory
import kotlinx.*
import kotlinx.android.synthetic.main.activity_main.*

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        buttonFetch.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.resultsIntent.send(MainIntent.FetchResult)
            }
        }
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        buttonFetch.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Characters -> {
                        progressBar.visibility = View.GONE
                        buttonFetch.visibility = View.GONE
                        renderList(it.response)
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetch.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(response: MarvelResponse) {
        recyclerView.visibility = View.VISIBLE
        response.let { listOfCharacters -> listOfCharacters.let { adapter.addData(it.data.results) } }
        adapter.notifyDataSetChanged()
    }
}
