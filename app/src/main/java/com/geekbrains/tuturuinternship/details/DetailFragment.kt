package com.geekbrains.tuturuinternship.details

import android.os.Bundle
import android.util.Log
import android.view.View
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.geekbrains.tuturuinternship.R
import com.geekbrains.tuturuinternship.dataFilms.DataFilms
import com.geekbrains.tuturuinternship.databinding.FragmentDetailsBinding
import com.geekbrains.tuturuinternship.AppState
import com.geekbrains.tuturuinternship.extensionFun.viewBindingFragment.ViewBindingFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: ViewBindingFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate), CoroutineScope by MainScope() {

    companion object {
        const val BUNDLE_EXTRA = "films"

        fun newInstance(bundle: Bundle): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    private val viewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailLoad()
    }

    private fun detailLoad() {
        arguments?.getParcelable<DataFilms>(BUNDLE_EXTRA)?.let {
            with(binding) {
                viewModel.liveData.observe(viewLifecycleOwner) { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            fragmentDetail.visibility = View.INVISIBLE
                        }
                        AppState.Loading -> {
                            fragmentDetail.visibility = View.INVISIBLE
                        }
                        is AppState.Success -> {
                            fragmentDetail.visibility = View.VISIBLE
                            Log.d("AppState.Success", "AppState.Success")
                            val urlPoster = "https://st.kp.yandex.net/images/film_big/${appState.filmsData[0].id}.jpg"
                            detailPoster.load(urlPoster) {
                                precision(Precision.EXACT)
                                error(R.drawable.ic_error)
                                scale(Scale.FILL)
                            }
                            detailSlogan.text = appState.filmsData[0].slogan
                            detailDescription.text = appState.filmsData[0].description
                        }
                    }
                }
            }
            viewModel.loadData(it.id)
        }
    }

    override fun onDestroyView() {
        cancel() // Отмена корутин
        super.onDestroyView()
    }
}