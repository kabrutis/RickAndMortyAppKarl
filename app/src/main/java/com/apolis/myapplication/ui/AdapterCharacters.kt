package com.apolis.myapplication.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.myapplication.R
import com.apolis.myapplication.databinding.RowLayoutCharacterBinding
import com.apolis.myapplication.model.Result
import kotlinx.android.synthetic.main.location_dialog.view.*

class AdapterCharacters(var myContext: Context) :
    RecyclerView.Adapter<AdapterCharacters.ViewHolderCharacters>() {
    var characterList = ArrayList<Result>()

    inner class ViewHolderCharacters(private val binding: RowLayoutCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.character = result
            binding.adapter = this@AdapterCharacters
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacters {
        return ViewHolderCharacters(RowLayoutCharacterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun setCharacterList(list: List<Result>) {
        characterList.clear()
        characterList.addAll(list)
        notifyDataSetChanged()
    }

    fun onCharacterClicked(character: Result) {
        val dialogLayoutLocation =
            LayoutInflater.from(myContext).inflate(R.layout.location_dialog, null)
        dialogLayoutLocation.text_view_location.text = "Location: ${character.location.name}"
        AlertDialog.Builder(myContext).setView(dialogLayoutLocation).show()

    }

    override fun onBindViewHolder(holder: ViewHolderCharacters, position: Int) {
        val result = characterList[position]
        holder.bind(result)
    }
}