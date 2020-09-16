package com.apolis.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.myapplication.databinding.RowLayoutCharacterBinding
import com.apolis.myapplication.model.Result

class AdapterCharacters:RecyclerView.Adapter<AdapterCharacters.ViewHolderCharacters>() {
    var characterList = ArrayList<Result>()
    inner class ViewHolderCharacters(private val binding: RowLayoutCharacterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: Result){
            binding.character = result
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacters {
        return ViewHolderCharacters(RowLayoutCharacterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
    fun setCharacterList(list:List<Result>){
        characterList.clear()
        characterList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolderCharacters, position: Int) {
        val result = characterList[position]
        holder.bind(result)
    }
}