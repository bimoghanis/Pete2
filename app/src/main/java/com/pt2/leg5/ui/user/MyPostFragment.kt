package com.pt2.leg5.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pt2.leg5.R
import com.pt2.leg5.ui.ulasan.UlasanAdapter
import com.pt2.leg5.ui.ulasan.UlasanViewModel

class MyPostFragment : Fragment() {
    private lateinit var recyclerViewMyPost: RecyclerView
    private lateinit var ulasanAdapter: UlasanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypost, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView dan adapter
        recyclerViewMyPost = view.findViewById(R.id.recyclerViewMyPost)
        ulasanAdapter = UlasanAdapter()

        // Atur layout manager untuk RecyclerView
        recyclerViewMyPost.layoutManager = LinearLayoutManager(requireContext())

        // Atur adapter ke RecyclerView
        recyclerViewMyPost.adapter = ulasanAdapter

        // Panggil method untuk mengambil data ulasan dari ViewModel
        observeUlasan()

    }

    private fun observeUlasan() {
        val ulasanViewModel = ViewModelProvider(requireActivity()).get(UlasanViewModel::class.java)
        ulasanViewModel.getAllUlasan().observe(viewLifecycleOwner) { ulasanList ->
            // Set data ulasan ke adapter
            ulasanAdapter.submitList(ulasanList)
        }

        ulasanAdapter.setOnItemClickListener { ulasan ->
            ulasanViewModel.deleteUlasan(ulasan)
        }
    }


}
