package com.example.retrofitmain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmain.R
import com.example.retrofitmain.model.CrypoModel


class RecyclerViewAdapter(private val cryptoList :ArrayList<CrypoModel>,private val listener : Listener):RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    interface Listener {
        fun onItemClick(cryptoModel: CrypoModel)
    }

    class RowHolder (view : View):RecyclerView.ViewHolder(view){
     lateinit var currency : TextView
      lateinit var price : TextView
        fun bind(cryptoModel:CrypoModel,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
        currency= itemView.findViewById(R.id.textCurrency)
        price=itemView.findViewById(R.id.textPrice)
            currency.text=cryptoModel.currency
            price.text=cryptoModel.price


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout ,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],listener)
    }

    override fun getItemCount(): Int {
      return cryptoList.count()
    }
}