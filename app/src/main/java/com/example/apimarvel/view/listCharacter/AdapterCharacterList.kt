package com.example.apimarvel.view.listCharacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.apimarvel.R
import com.example.apimarvel.remoteDataSource.model.CharacterModel
import androidx.navigation.fragment.NavHostFragment.findNavController

class AdapterCharacterList(
    private val items: MutableList<CharacterModel>,
    private val context: Fragment
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MyViewHolder -> {
                holder.bind(items[position])
            }
        }

        holder.itemView.setOnClickListener{
            val id = items[position].id

            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(id = id)
            findNavController(context).navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class MyViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView = itemView.findViewById<TextView>(R.id.nameCharacterTextView)

    fun bind(characterModel: CharacterModel){
        nameTextView.text = characterModel.name
    }
}