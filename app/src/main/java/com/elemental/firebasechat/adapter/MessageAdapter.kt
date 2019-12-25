package com.elemental.firebasechat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elemental.firebasechat.R
import com.elemental.firebasechat.adapter.MessageAdapter.ViewHolder
import com.elemental.firebasechat.model.Chat

class MessageAdapter(private val context: Context, private val chats:List<Chat>):
    RecyclerView.Adapter<ViewHolder>() {

    companion object{
        private const val MSG_TYPE_LEFT=0
        private const val MSG_TYPE_RIGHT=1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType== MSG_TYPE_RIGHT){
            val view=LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false)
            return ViewHolder(view)
        }
        else{
            val view=LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false)
            return ViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat:Chat=chats[position]
        holder.show_message?.text=chat.message
    }
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val show_message: TextView?=itemView.findViewById(R.id.show_message)

    }

    override fun getItemViewType(position: Int): Int {
        //this must be the current user
        if(chats[position].sender == "pm"){
            return MSG_TYPE_RIGHT
        }
        else
        {
            return MSG_TYPE_LEFT
        }
    }
}