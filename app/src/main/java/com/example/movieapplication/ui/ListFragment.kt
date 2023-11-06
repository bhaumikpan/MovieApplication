package com.example.movieapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.data.Movie


class ListFragment : Fragment() {

    private lateinit var moviesToDisplay: List<Movie>

    private lateinit var rv: RecyclerView
    private lateinit var loading: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_list_view,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(activity!!)[MovieViewModel::class.java]
        rv = view.findViewById(R.id.recycle_view)
        loading = view.findViewById(R.id.view_get_data_progress)

        // Call api get data
        viewModel.getData()

        observeData()

        moviesToDisplay = ViewModelProvider(activity!!)[MovieViewModel::class.java].listToDisplay


        // create a vertical layout manager
        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        (rv as RecyclerView?)?.layoutManager = layoutManager
        (rv as RecyclerView?)?.setHasFixedSize(true)

        (rv as RecyclerView?)?.addItemDecoration(
            DividerItemDecoration(
                view.context,
                layoutManager.orientation
            )
        )

        val movieAdapter = MovieAdapter(
            moviesToDisplay
        ) {
            val id = it.tag as Int
            val movieClicked: Movie = moviesToDisplay.filter { movie ->
                movie.id == id
            }[0]

            val activity = view.context as AppCompatActivity
            val myFragment: Fragment = MovieDetailsFragment(id)
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, myFragment).addToBackStack(null).commit()

            Toast.makeText(context, movieClicked.originalTitle, Toast.LENGTH_LONG).show()

        }
        (rv as RecyclerView?)?.adapter = movieAdapter

        var isLoading = false

        (rv as RecyclerView?)?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == moviesToDisplay.size - 1) {
                        loadMore()
                        isLoading = true
                        ((rv as RecyclerView?)?.adapter as MovieAdapter).notifyDataSetChanged()
                    }
                }
            }
        })
    }

    fun loadMore() {
        val viewModel = ViewModelProvider(activity!!)[MovieViewModel::class.java]
        viewModel.loadMore()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        val viewModel = ViewModelProvider(activity!!)[MovieViewModel::class.java]
        viewModel.data.observe(
            this
        ) { moviesToDisplay ->
            when (moviesToDisplay) {
                true -> {
                    loading.visibility = View.GONE
                    (rv as RecyclerView?)?.adapter?.notifyDataSetChanged()
                }
                else -> {
                    // Keep loader on
                }
            }
        }
    }
}