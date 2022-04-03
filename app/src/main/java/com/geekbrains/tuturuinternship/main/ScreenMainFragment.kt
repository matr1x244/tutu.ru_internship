package com.geekbrains.tuturuinternship.main

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.*
import com.geekbrains.tuturuinternship.R
import com.geekbrains.tuturuinternship.dataFilms.DataFilms
import com.geekbrains.tuturuinternship.databinding.FragmentScreenMainBinding
import com.geekbrains.tuturuinternship.details.DetailFragment
import com.geekbrains.tuturuinternship.extensionFun.showSnackBarNoAction
import com.geekbrains.tuturuinternship.extensionFun.viewBindingFragment.ViewBindingFragment
import com.geekbrains.tuturuinternship.main.adapter.RecyclerViewFragmentAdapter
import com.geekbrains.tuturuinternship.AppState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreenMainFragment: ViewBindingFragment<FragmentScreenMainBinding>(FragmentScreenMainBinding::inflate) {

    companion object{
        fun newInstance() = ScreenMainFragment()
    }

    private val viewModel: ScreenViewModel by viewModel()
    private var adapterFilms: RecyclerViewFragmentAdapter? = null
    private lateinit var bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior()
        detailsNext()
    }

    private fun bottomSheetBehavior(){
            bottomSheetBehavior = BottomSheetBehavior.from(binding.includedBsl.bottomSheetContainer)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetBehavior.setPeekHeight(100,true)
            bottomSheetBehavior.isHideable = false

            bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> animationBottomSheetBehavior()
                }
            }
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    //...
                }
            })
    }

    private fun animationBottomSheetBehavior() {
        val transition = TransitionSet()
        val slide = Slide()
        slide.duration = 5000
        slide.slideEdge = Gravity.BOTTOM
        transition.addTransition(slide)
        TransitionManager.beginDelayedTransition(binding.includedBsl.bottomSheetContainer, transition)
        binding.includedBsl.bottomThanks.visibility = View.VISIBLE
    }

    private fun detailsNext() {
        binding.recyclerView.adapter = adapterFilms
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getFilmsFromLoad()
    }

    private fun renderData(appState: AppState) = with(binding){
        when(appState){
            is AppState.Success -> {
                progressBar.visibility = View.GONE
                adapterFilms = RecyclerViewFragmentAdapter(object : OnItemViewClickListener{
                    override fun onItemViewClick(films: DataFilms) {
                        val manager = activity?.supportFragmentManager
                        manager?.let {
                            val bundle = Bundle().apply {
                                putParcelable(DetailFragment.BUNDLE_EXTRA, films)
                            }
                                    manager.beginTransaction().setCustomAnimations(
                                            R.anim.slide_in,
                                            R.anim.fade_out,
                                            R.anim.fade_in,
                                            R.anim.slide_out)
                                    .replace(R.id.containerMainActivity,DetailFragment.newInstance(bundle)) // передадим bundle ключ значение
                                    .addToBackStack(null).commit()
                        }
                    }
                }).apply { setData(appState.filmsData) }
               recyclerView.adapter = adapterFilms
            }
            is AppState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                fragmentScreenMain.showSnackBarNoAction(resources.getString(R.string.error_message))
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(films: DataFilms)
    }
}