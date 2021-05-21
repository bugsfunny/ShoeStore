package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shoeListViewModel = viewModel
        binding.shoe = Shoe()
        binding.buttonCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
            )
        )
        binding.buttonSave.setOnClickListener {
            viewModel.addShoe(viewModel.shoe)
            viewModel.shoe = Shoe()
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}