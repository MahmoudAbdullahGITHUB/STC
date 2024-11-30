package com.example.stc.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.stc.data.remote.model.response.charachter.Result
import com.example.stc.databinding.FragmentDetailsBinding
import com.example.stc.ui.details.adapter.ComicsItemsAdapter
import com.example.stc.ui.details.adapter.SeriesItemsAdapter

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    private var item: Result? = null
    private var imageUrl: String? = null

    private var comicsAdapter: ComicsItemsAdapter? = null
    private var seriesAdapter: SeriesItemsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item = args.character
        initViews()

    }

    private fun initViews() {
        imageUrl = "${item?.thumbnail?.path}.${item?.thumbnail?.extension}"

        Glide.with(binding.characterImage.context)
            .load(imageUrl)
            .into(binding.characterImage)

        binding.fullscreenContent.text = item?.name

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        comicsAdapter = ComicsItemsAdapter()
        seriesAdapter = SeriesItemsAdapter()
        binding.comicsItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = this@DetailsFragment.comicsAdapter

        }
        comicsAdapter?.submitList(item?.comics?.items)

        binding.series.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = this@DetailsFragment.seriesAdapter

        }
        seriesAdapter?.submitList(item?.series?.items)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}