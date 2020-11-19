package com.dgm.pruebagft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgm.pruebagft.R
import com.dgm.pruebagft.repository.remote.model.DetailsResponse
import kotlinx.android.synthetic.main.info_row.view.*
import java.util.ArrayList

class InfoAdapter(val cardList: ArrayList<DetailsResponse.Card>) :  RecyclerView.Adapter<InfoAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val id: TextView = view.idCardTV
        val type: TextView = view.typeCardTV
        val name: TextView = view.nameCardTV
        val deposits: TextView = view.depositsCardTV
        val withdrawals: TextView = view.withdrawalsCardTV
        val balance: TextView = view.balanceCardTV

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.info_row, parent, false)
        return ViewHolder(viewRow)
    }

    override fun getItemCount() = cardList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cardList.let {
            holder.id.text = "Id: ${it[position]._id}"
            holder.type.text = "Type: ${it[position].type}"
            holder.name.text = "Name ${it[position].name}"
            holder.deposits.text = "Deposits: ${it[position].deposits}"
            holder.withdrawals.text = "Withdrawals ${it[position].withdrawals}"
            holder.balance.text = "Balance: ${it[position].balance}"
        }
    }
}