package com.example.managementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AreaAdapter(private val items: ArrayList<ListArea>): RecyclerView.Adapter<AreaAdapter.AreaViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.area}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return AreaViewHolder(inflatedView)
    }

    class AreaViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: ListArea) {
            //view.contentlist.text = item.area
            view.setOnClickListener(listener)
        }
    }
}
