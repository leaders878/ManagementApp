package com.example.managementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SubjectAdapter(private val items: ArrayList<ListSubject>): RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.subject}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return SubjectViewHolder(inflatedView)
    }

    class SubjectViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: ListSubject) {
            //view.contentlist.text = item.subject
            view.setOnClickListener(listener)
        }
    }
}
