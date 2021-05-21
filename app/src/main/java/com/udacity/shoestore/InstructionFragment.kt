package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {
    private var _binding: FragmentInstructionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shoeListDirections = InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment()
        binding.goButton.setOnClickListener {
            if (connectUser()){
                findNavController().navigate(shoeListDirections)
            } else {
                Toast.makeText(context, "There is an error", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun connectUser(): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
        sharedPref.edit().putBoolean("IS_CONNECTED", true).apply()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}