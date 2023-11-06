package com.example.movieapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.R
import com.example.movieapplication.data.Movie
import com.squareup.picasso.Picasso


class MovieDetailsFragment(private val movieId: Int) : Fragment() {

    private var movieSelected: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(activity!!)[MovieViewModel::class.java]

        movieSelected = viewModel.listToDisplay.filter { movie ->
            movie.id == movieId
        }[0]

        return if (movieSelected != null) {
            inflater.inflate(
                R.layout.fragment_movie_detail_view,
                container, false
            )
        } else {
            null
        }
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val movieTXT: TextView = itemView.findViewById(R.id.txt_movie_name)
        val movieRating: TextView = itemView.findViewById(R.id.txt_movie_rating)
        val movieImage: ImageView = itemView.findViewById(R.id.image_movie)
        val movieDescription: TextView = itemView.findViewById(R.id.txt_movie_description)

        val imageContext = movieImage.context

        itemView.tag = movieSelected?.id

        movieTXT.text = movieSelected?.title

        movieRating.text = (movieSelected?.popularity).toString()

        Picasso.with(imageContext)
            .load(movieSelected?.getBackdropPathWithUrl())
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .resize(800, 600)
            .into(movieImage)

        movieDescription.text = movieSelected?.overview
    }
}