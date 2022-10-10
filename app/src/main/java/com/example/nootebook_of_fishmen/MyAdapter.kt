package com.example.nootebook_of_fishmen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (listArray:ArrayList<ListItem>,context: Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listArrayR = listArray
    var contextR = context




    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvContent = view.findViewById<TextView>(R.id.textContent)
        val tvIm = view.findViewById<ImageView>(R.id.tvIm)

        fun bind(listItem: ListItem,context: Context)
        {
            tvTitle.text = listItem.titleText
            var textCon = listItem.contentText.substring(0,10) + "..."
            tvContent.text = textCon
            tvIm.setImageResource(listItem.image_id)
            itemView.setOnClickListener(){
                Toast.makeText(context,"Pressed : ${tvTitle.text}",Toast.LENGTH_SHORT).show()
                val i = Intent(context,ContentActivity::class.java).apply {
                    putExtra("title",tvTitle.text.toString())
                    putExtra("content",listItem.contentText)
                    putExtra("image",listItem.image_id)
                }
                context.startActivity(i)



            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem = listArrayR.get(position)
        holder.bind(listItem,contextR)
    }

    override fun getItemCount(): Int {
        return listArrayR.size
    }
    fun updateAdapter (listArray:List<ListItem>)
    {

        listArrayR.clear()
        listArrayR.addAll(listArray)
        notifyDataSetChanged()
    }
}