package com.example.guitaristsstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guitaristsstats.data.GuitarEntity
import com.example.guitaristsstats.databinding.ListItemBinding

class GuitarsListAdapter(private val guitarsList: List<GuitarEntity>,
    private val listener: ListItemListener):
    RecyclerView.Adapter<GuitarsListAdapter.ViewHolder>(){

    val selectedGuitars = arrayListOf<GuitarEntity>()

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = guitarsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val guitar = guitarsList[position]
        with(holder.binding) {
            guitarText.text = guitar.name
            root.setOnClickListener{
                //notifies the listener class the user has hit this entity and to view this id
                listener.editGuitarist(guitar.id)
            }
            fab.setOnClickListener{
                if(selectedGuitars.contains(guitar)) {
                    selectedGuitars.remove(guitar)
                    fab.setImageResource(R.drawable.ic_user)
                } else {
                    selectedGuitars.add(guitar)
                    fab.setImageResource(R.drawable.ic_check)
                }
                listener.onItemSelectionChanged()
            }
            fab.setImageResource(
                if(selectedGuitars.contains(guitar)) {
                    R.drawable.ic_check
                } else {
                    R.drawable.ic_user
                }
            )
        }
    }

    interface ListItemListener {
        fun editGuitarist(guitarId: Int)
        fun onItemSelectionChanged()
    }
}