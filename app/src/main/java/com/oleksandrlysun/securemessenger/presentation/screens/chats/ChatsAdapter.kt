package com.oleksandrlysun.securemessenger.presentation.screens.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.presentation.views.AvatarImageView
import java.util.*

class ChatsAdapter : RecyclerView.Adapter<ChatsAdapter.ViewHolder>() {

    private val items = LinkedList<Chat>()

    var listener: ((Chat) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(chats: List<Chat>) {
        items.clear()
        items.addAll(chats)
        notifyDataSetChanged()
    }

    fun addItem(item: Chat) {
        items.addFirst(item)
        notifyItemInserted(0)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView = itemView.findViewById<AvatarImageView>(R.id.avatarImageView)
        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        private val messageTextView = itemView.findViewById<TextView>(R.id.messageTextView)
        private val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)

        fun bind(item: Chat) {
            avatarImageView.setUser(item.other)
            nameTextView.text = item.other.fullName
            messageTextView.text = "last message"//item.message
            dateTextView.text = "21.02.2021"//item.date
            itemView.setOnClickListener { listener?.invoke(item) }
        }
    }
}