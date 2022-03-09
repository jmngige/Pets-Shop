package com.starsolns.pets.views.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.pets.databinding.FragmentListBinding
import com.starsolns.pets.views.model.Pet
import com.starsolns.pets.views.ui.adapters.PetsAdapter
import com.starsolns.pets.views.viewmodel.MainViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PetsAdapter
    private var petsList = mutableListOf<Pet>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.pets.observe(requireActivity(), Observer {pets->
            petsList.addAll(pets)
            adapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(requireActivity(), Observer { isLoading->
            binding.loadingData.visibility = if(isLoading) View.VISIBLE else View.INVISIBLE
        })

        viewModel.isError.observe(requireActivity(), Observer { isError->
            binding.errorMessage.visibility = if (isError) View.VISIBLE else View.INVISIBLE
        })

        adapter = PetsAdapter(requireActivity(),petsList, object: PetsAdapter.ItemClickListener{
            override fun onItemClick(pet: Pet) {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(pet)
                findNavController().navigate(action)
            }

        })
        binding.petsList.layoutManager = LinearLayoutManager(requireActivity())
        binding.petsList.adapter = adapter

        viewModel.getPets()

       binding.swipeToRefresh.setOnRefreshListener {
           binding.petsList.visibility = View.GONE
          Handler().postDelayed({
              binding.petsList.visibility = View.VISIBLE
              viewModel.getPets()
              binding.swipeToRefresh.isRefreshing = false
          }, 3000)
       }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}