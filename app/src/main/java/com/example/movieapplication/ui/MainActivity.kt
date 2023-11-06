package com.example.movieapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loaderView = findViewById<ProgressBar>(R.id.view_get_data_progress)

        viewModel.data

        addFragmentToActivity(ListFragment())


    }

    var curFragment: Fragment? = null

    private fun addFragmentToActivity(fragment: ListFragment?) {
        if (fragment == null) return
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.fragment_container_view, fragment)
        tr.commitAllowingStateLoss()
        curFragment = fragment
    }
}