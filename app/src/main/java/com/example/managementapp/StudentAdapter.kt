package com.example.managementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val items: ArrayList<ListStudent>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.studentname}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return StudentViewHolder(inflatedView)
    }

    class StudentViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: ListStudent) {
            //view.contentlist.text = item.studentname
            view.setOnClickListener(listener)
        }
    }
}
