package com.oleksandrlysun.securemessenger.presentation.screens.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.Message
import com.oleksandrlysun.securemessenger.presentation.utils.DateFormatter
import java.util.*

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 0
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 1
    }

    private val items = LinkedList<Message>()

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isMine) VIEW_TYPE_MESSAGE_SENT else VIEW_TYPE_MESSAGE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResId = when (viewType) {
            VIEW_TYPE_MESSAGE_SENT -> R.layout.item_message_sent
            else -> R.layout.item_message_received
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Message>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Message) {
        items.addFirst(item)
        notifyItemInserted(0)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView = itemView.findViewById<TextView>(R.id.messageTextView)
        private val timeTextView = itemView.findViewById<TextView>(R.id.timeTextView)

        fun bind(item: Message) {
            messageTextView.text = item.body
            if (item.date != null) {
                timeTextView.text = DateFormatter.timeAsString(item.date)
            }
        }
    }
}