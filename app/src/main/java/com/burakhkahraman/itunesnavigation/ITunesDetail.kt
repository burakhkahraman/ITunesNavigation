package com.burakhkahraman.itunesnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.burakhkahraman.itunesnavigation.databinding.FragmentITunesDetailBinding
import com.burakhkahraman.itunesnavigation.viewmodel.ITunesDetailViewModel
import com.burakhkahraman.itunessearch.network.response.Result

class ITunesDetail : Fragment() {
    private var _binding: FragmentITunesDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ITunesDetailArgs by navArgs()
    private val viewModel: ITunesDetailViewModel by viewModels()

    private lateinit var imageSearch: ImageView
    private lateinit var artistName: TextView
    private lateinit var collectionName: TextView
    private lateinit var trackName: TextView
    private lateinit var collectionPrice: TextView
    private lateinit var releaseDate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentITunesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageSearch = binding.imageView
        artistName = binding.artistNameTextView
        collectionName = binding.collectionNameTextView
        trackName = binding.trackNameTextView
        collectionPrice = binding.collectionPriceTextView
        releaseDate = binding.releaseDateTextView

        viewModel.setResult(args.Result)

        viewModel.result.observe(viewLifecycleOwner, Observer { result: Result ->
            displayResult(result)
        })
    }

    private fun displayResult(result: Result) {
        Glide.with(this)
            .load(result.artworkUrl100)
            .into(imageSearch)

        binding.artistNameTextView.text = "Artist Name: ${result.artistName}"
        binding.collectionNameTextView.text = "Collection Name: ${result.collectionName}"
        binding.trackNameTextView.text = "Track Name: ${result.trackName}"
        binding.collectionPriceTextView.text = "Collection Price: ${result.collectionPrice} ${result.currency}"
        binding.releaseDateTextView.text = "Release Date: ${result.releaseDate}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
