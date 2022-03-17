package com.example.managementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ClassAdapter(private val items: ArrayList<ListClass>): RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.classname}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ClassViewHolder(inflatedView)
    }

    class ClassViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: ListClass) {
            //view.contentlist.text = item.classname
            view.setOnClickListener(listener)
        }
    }
}
