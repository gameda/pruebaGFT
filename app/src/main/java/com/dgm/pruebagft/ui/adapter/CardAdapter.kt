package com.dgm.pruebagft.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgm.pruebagft.R
import com.dgm.pruebagft.repository.remote.model.CatalogResponse
import kotlinx.android.synthetic.main.card_row.view.*
import java.util.ArrayList

class CardAdapter(var cardList: ArrayList<CatalogResponse.CardResponse.CardDetail>,
                  var clickListener: OnItemClickListener)
    :  RecyclerView.Adapter<CardAdapter.ViewHolder>(){

    private var onItemClickListener: OnItemClickListener? = null


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val type: TextView = view.typeTV
        val name: TextView = view.nameTV

        fun initializeClick(action:OnItemClickListener){
            itemView.isSelected = true
            itemView.setOnClickListener{
                action.onItemClick(adapterPosition)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.ViewHolder {
        val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.card_row, parent, false)
        return ViewHolder(viewRow)
    }

    override fun getItemCount() = cardList?.size


    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        cardList?.let {
            holder.type.text = "Type: ${it[position].type}"
            holder.name.text = "Name ${it[position].name}"
        }

        holder.initializeClick(clickListener)
       /* holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }*/

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}