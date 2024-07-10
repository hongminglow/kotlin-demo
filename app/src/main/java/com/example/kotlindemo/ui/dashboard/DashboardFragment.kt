package com.example.kotlindemo.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.adapter.SimpleAdapter
import com.example.kotlindemo.databinding.FragmentDashboardBinding
import com.example.kotlindemo.R

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val dataList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find RecyclerView from the inflated view
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Set up LayoutManager and Adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = SimpleAdapter(requireContext(), dataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}