package com.example.searchviewrecyclernavgraphflowsealed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.searchviewrecyclernavgraphflowsealed.databinding.Fragment2Binding

class Fragment2:Fragment() {
    private lateinit var binding: Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie =arguments?.getParcelable<Movies>("key")
        binding.text.text = movie.toString()
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_fragment22_to_fragment12)
        }

    }
}