package com.oleksandrlysun.securemessenger.presentation.screens.createchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.views.AvatarImageView

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var items: List<User> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView = itemView.findViewById<AvatarImageView>(R.id.avatarImageView)
        private val fullNameTextView = itemView.findViewById<TextView>(R.id.fullNameTextView)
        private val userNameTextView = itemView.findViewById<TextView>(R.id.userNameTextView)

        fun bind(item: User) {
            avatarImageView.setUser(item)
            fullNameTextView.text = item.fullName
            userNameTextView.text = item.userName
            itemView.setOnClickListener { listener?.invoke(item) }
        }
    }
}