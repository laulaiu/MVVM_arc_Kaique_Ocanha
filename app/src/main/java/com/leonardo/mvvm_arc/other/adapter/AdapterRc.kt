package com.leonardo.mvvm_arc.other.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leonardo.mvvm_arc.R
import com.leonardo.mvvm_arc.data.model.LiveModelo

class AdapterRc(private  var itemClick: (LiveModelo) -> Unit) : RecyclerView.Adapter<AdapterRc.Holder>() {

    private var lista  = mutableListOf<LiveModelo>()

    public fun setList(lista: List<LiveModelo>){
        this.lista = lista.toMutableList()
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)

        fun clickFun (liveM : LiveModelo, itemC : (LiveModelo) -> Unit){

            itemView.setOnClickListener{
                itemC(liveM)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.live_adapter_layout, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.authorTextView.text = lista.get(position).author
        holder.titleTextView.text = lista.get(position).title
        holder.linkTextView.text = lista.get(position).link
        Glide.with(holder.itemView.context).load(lista.get(position).thumbnailUrl).into(holder.thumbnailImageView)

        holder.clickFun(lista.get(position), itemClick)

    }

}