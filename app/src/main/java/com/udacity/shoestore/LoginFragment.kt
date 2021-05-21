package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val welcomeDirection = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        val shoeListDirection = LoginFragmentDirections.actionLoginFragmentToShoeListFragment()
        if (isUserConnected()) {
            findNavController().navigate(shoeListDirection)
        }
        binding.buttonLogin.setOnClickListener(Navigation.createNavigateOnClickListener(welcomeDirection))
        binding.buttonSignup.setOnClickListener(Navigation.createNavigateOnClickListener(welcomeDirection))
    }

    private fun isUserConnected(): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean("IS_CONNECTED", false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}