package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding

class ShoeListFragment : Fragment() {
    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.shoeList.observe(viewLifecycleOwner) {
            for (shoe in it) {
                val innerBinding = ShoeItemBinding.inflate(layoutInflater)
                innerBinding.shoe = shoe
                binding.shoeList.addView(innerBinding.root)
            }
        }
        val actionShoeListFragmentToShoeDetailFragment =
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        val createNavigateOnClickListener = Navigation.createNavigateOnClickListener(
            actionShoeListFragmentToShoeDetailFragment
        )
        binding.fab.setOnClickListener(createNavigateOnClickListener)
        val shoe = arguments?.let { ShoeListFragmentArgs.fromBundle(it).shoe }
        shoe?.let {
            viewModel.addShoe(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.loginFragment) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
            sharedPref.edit().putBoolean("IS_CONNECTED", false).apply()
            findNavController().navigate(R.id.loginFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}