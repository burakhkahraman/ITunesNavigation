package com.burakhkahraman.itunesnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burakhkahraman.itunesnavigation.databinding.FragmentITunesListBinding
import com.burakhkahraman.itunesnavigation.viewmodel.ITunesListViewModel

class ITunesList : Fragment() {
    private var _binding: FragmentITunesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ITunesListViewModel by viewModels()
    private var recyclerITunesAdapter = ITunesAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentITunesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searching: androidx.appcompat.widget.SearchView = binding.searchView
        val searchBtn: Button = binding.searchButton

        searchBtn.setOnClickListener {
            val query = searching.query.toString()
            if (query.isNotEmpty()) {
                viewModel.searchByTerm(query)
            }
        }

        val recyclerView: RecyclerView = binding.itunesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerITunesAdapter

        viewModel.results.observe(viewLifecycleOwner, Observer { results ->
            recyclerITunesAdapter.updateData(results)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
