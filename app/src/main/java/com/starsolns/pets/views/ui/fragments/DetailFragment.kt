package com.starsolns.pets.views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.starsolns.pets.R
import com.starsolns.pets.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args : DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        val currentPet = args.currentPet

        Glide.with(this).load(currentPet.imageUrl).placeholder(R.drawable.image_animation).into(binding.detailPetImage)
        binding.detailPetName.text = currentPet.name
        binding.detailPetDescription.text = currentPet.description

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}