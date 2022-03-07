package com.example.check24app.ui.list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.check24app.R
import com.example.check24app.common.gone
import com.example.check24app.common.showSnack
import com.example.check24app.common.showToast
import com.example.check24app.common.visible
import com.example.check24app.databinding.FragmentShapesBinding
import com.example.check24app.ui.base.BaseFragment
import com.example.check24app.ui.list.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShapesFragment : BaseFragment<FragmentShapesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentShapesBinding
        get() = FragmentShapesBinding::inflate

    private val viewModel: ShapesViewModel by viewModels()

    lateinit var photosAdapter: ShapesAdapter

    var snackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initObservations()
    }

    fun setupViews() {
        context?.let { ctx ->
            // Photos RecyclerView
            photosAdapter = ShapesAdapter() { giphy, _ ->
                var bundle = bundleOf("shapesDetail" to giphy)
                findNavController().navigate(R.id.action_shapesFragment_to_detailsFragment, bundle)
            }
//            photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            bi.rvShapes.adapter = photosAdapter

            // NestedScrollView
            bi.srlShapes.setOnRefreshListener {
                viewModel.getShapes()
                bi.srlShapes.isRefreshing = true
            }

            bi.tvCopyrights.setOnClickListener {
                openCopyRightWindow("https://m.check24.de/rechtliche-hinweise/?deviceoutput=app", requireContext())
            }
        }
    }


    fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    bi.rvShapes.gone()
                    bi.progressPhotos.visible()
                }

                is LoadingNextPageState -> {
                    bi.progressPhotos.gone()
                    showToast(getString(R.string.loading))
                }

                is ContentState -> {
                    bi.rvShapes.visible()
                    bi.progressPhotos.gone()
                }

                is ErrorState -> {
                    bi.progressPhotos.gone()
                    bi.srlShapes.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.getShapes()
                    }
                    bi.srlShapes.isRefreshing = false
                }

                is ErrorNextPageState -> {
                    bi.srlShapes.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.getShapes()
                    }

                    bi.srlShapes.isRefreshing = false
                }
            }
        }

        viewModel.photosListLiveData.observe(viewLifecycleOwner) { shapesListModel ->
            bi.tvHeader.text = shapesListModel.title
            bi.tvSubTitle.text = shapesListModel.subtitle
            photosAdapter.updateItems(shapesListModel!!.shapesList!!)
            bi.srlShapes.isRefreshing = false
        }
    }

    fun openCopyRightWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

}