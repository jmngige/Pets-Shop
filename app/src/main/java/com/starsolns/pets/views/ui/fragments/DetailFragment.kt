package com.starsolns.pets.views.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

        currentPet.imageUrl?.let {url->
            setLayoutBackGround(url)
        }

        return binding.root
    }

    private fun setLayoutBackGround(url: String?) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                   Palette.from(resource)
                       .generate(){
                           val color = it?.lightMutedSwatch?.rgb ?: 0
                           binding.detailLayout.setBackgroundColor(color)
                       }
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}