package com.example.td4_exo4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tdm_s5_e1.ContactDTO
import com.example.tdm_s5_e1.MyCommunicator
import com.example.tdm_s5_e1.R

class ContactAdapter(private val context: Context?, private var data: List<ContactDTO>):
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>()
{
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameContact = view.findViewById<TextView>(R.id.contact_name) as TextView
        val numContact= view.findViewById<TextView>(R.id.contact_mail) as TextView
        val img=view.findViewById<ImageView>(R.id.sendSms) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameContact.text=  data[position].name
        holder.numContact.text=data[position].mail
        holder.img.setOnClickListener {
            val myCommunicator = context as MyCommunicator
            myCommunicator.displayDetails(data[position])
        }
    }

    override fun getItemCount(): Int =data.size

}