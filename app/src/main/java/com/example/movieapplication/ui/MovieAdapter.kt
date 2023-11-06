package com.example.movieapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.data.Movie
import com.squareup.picasso.Picasso


class MovieAdapter(
    private val dataToDisplay: List<Movie>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieItem: Movie? = null
        var movieTXT: TextView = itemView.findViewById(R.id.txt_movie_name)
        var movieRating: TextView = itemView.findViewById(R.id.txt_movie_rating)
        var movieImage: ImageView = itemView.findViewById(R.id.image_movie)
        var movieDescription: TextView = itemView.findViewById(R.id.txt_movie_description)
    }

    class LoadingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return when (viewType == VIEW_TYPE_ITEM) {
            true -> {
                MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
                )
            } else -> {
                MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataToDisplay.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            populateItemRows(holder as MovieViewHolder?, position)
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder as LoadingViewHolder?, position)
        }
    }

    private fun populateItemRows(holder: MovieViewHolder?, position: Int) {

        val movie = dataToDisplay[position]
        holder?.movieItem = movie

        val imageContext = holder?.movieImage?.context

        holder?.itemView?.tag = movie.id
        holder?.itemView?.setOnClickListener(onClickListener)
        holder?.movieTXT?.text = movie.title
        holder?.movieRating?.text = (movie.popularity % 100).toString()

        Picasso.with(imageContext)
            .load(movie.getBackdropPathWithUrl())
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .resize(400, 400)
            .into(holder?.movieImage)

        holder?.movieDescription?.text = movie.overview
    }



    private fun showLoadingView(loadingViewHolder: LoadingViewHolder?, position: Int) {

    }
}