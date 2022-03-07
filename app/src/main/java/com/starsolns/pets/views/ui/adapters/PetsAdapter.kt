package com.starsolns.pets.views.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.starsolns.pets.R
import com.starsolns.pets.views.Pet
import com.starsolns.pets.views.ui.fragments.ListFragmentDirections

class PetsAdapter(
    private val context: Context,
    private val petsList: List<Pet>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<PetsAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(pet: Pet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentPet = petsList[position]
        holder.bind(currentPet)
        Glide.with(context).load(currentPet.imageUrl).placeholder(R.drawable.image_animation)
            .into(holder.image)
//        holder.petItem.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToDetailFragment(currentPet)
//            Navigation.findNavController(holder.itemView).navigate(action)
//        }
    }

    override fun getItemCount() = petsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.pet_name)
        var description: TextView = itemView.findViewById(R.id.pet_description)
        var image: ImageView = itemView.findViewById(R.id.pet_image)
        var petItem: ConstraintLayout = itemView.findViewById(R.id.petItem)

        fun bind(pet: Pet) {
            name.text = pet.name
            description.text = pet.description
            itemView.setOnClickListener {
                itemClickListener.onItemClick(pet)
            }
        }

    }

}