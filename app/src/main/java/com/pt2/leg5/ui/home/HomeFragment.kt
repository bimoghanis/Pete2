package com.pt2.leg5.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pt2.leg5.R

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pt2.leg5.ui.ulasan.UlasanAdapter
import com.pt2.leg5.ui.ulasan.UlasanViewModel

// ...

class HomeFragment : Fragment() {
    private lateinit var recyclerViewUlasan: RecyclerView
    private lateinit var ulasanAdapter: UlasanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView dan adapter
        recyclerViewUlasan = view.findViewById(R.id.recyclerViewUlasan)
        ulasanAdapter = UlasanAdapter()

        // Atur layout manager untuk RecyclerView
        recyclerViewUlasan.layoutManager = LinearLayoutManager(requireContext())

        // Atur adapter ke RecyclerView
        recyclerViewUlasan.adapter = ulasanAdapter

        // Panggil method untuk mengambil data ulasan dari ViewModel
        observeUlasan()

        val searchButton = view.findViewById<Button>(R.id.searchButton)

        val searchBar = view.findViewById<EditText>(R.id.searchBar)

        searchButton.setOnClickListener {
            val query = searchBar.text.toString()
            searchUlasan(query)

        }
    }

    private fun observeUlasan() {
        val ulasanViewModel = ViewModelProvider(requireActivity()).get(UlasanViewModel::class.java)
        ulasanViewModel.getAllUlasan().observe(viewLifecycleOwner) { ulasanList ->
            // Set data ulasan ke adapter
            ulasanAdapter.submitList(ulasanList)
        }
    }

    private fun searchUlasan(query: String) {
        val ulasanViewModel = ViewModelProvider(requireActivity()).get(UlasanViewModel::class.java)
        ulasanViewModel.searchUlasan(query).observe(viewLifecycleOwner) { ulasanList ->
            if (ulasanList.isNotEmpty()) {
                // Jika ada hasil pencarian, set data ulasan ke adapter
                ulasanAdapter.submitList(ulasanList)
                Toast.makeText(requireContext(), "Yeay, ditemukan!😘", Toast.LENGTH_SHORT).show()
            } else {
                // Jika tidak ada hasil pencarian, tampilkan pesan "Tidak ditemukan"
                Toast.makeText(requireContext(), "Coba cari yang lain ya!!☺", Toast.LENGTH_SHORT).show()
            }
        }
    }
}