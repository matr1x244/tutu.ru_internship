package com.geekbrains.tuturuinternship.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.geekbrains.tuturuinternship.R
import com.geekbrains.tuturuinternship.dataFilms.DataFilms
import com.geekbrains.tuturuinternship.databinding.RecycleItemBinding
import com.geekbrains.tuturuinternship.main.ScreenMainFragment

class RecyclerViewFragmentAdapter(private val itemClickListener: ScreenMainFragment.OnItemViewClickListener): RecyclerView.Adapter<RecyclerViewFragmentAdapter.ViewHolder>() {

    private var movieList: MutableList<DataFilms> = ArrayList()
    private lateinit var binding: RecycleItemBinding // макет

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newMovieList: List<DataFilms>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(movieList[position])
     }

    override fun getItemCount() = movieList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(films: DataFilms) = with(binding) {
            val urlPoster = "https://st.kp.yandex.net/images/film_big/${films.id}.jpg"
            poster.load(urlPoster) {
                precision(Precision.EXACT)
                error(R.drawable.ic_error)
                scale(Scale.FILL)}
            name.text = films.name
            tmdb.text = films.imdb.toString()
            dateRelease.text = films.year.toString()

            root.setOnClickListener { itemClickListener.onItemViewClick(films)
                Toast.makeText(itemView.context,"Загружаем фильм: ${name.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

