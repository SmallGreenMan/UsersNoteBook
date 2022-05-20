package com.gmail.avoishel.usersnotebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.gmail.avoishel.usersnotebook.utils.PicassoUtil
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class UsersListAdapter() : RecyclerView.Adapter<UsersListAdapter.UserItemViewHolder>() {

    private var userList: List<UserModel>? = null
    private var onItemClickListener: ((UserModel) -> Unit)? = null

    fun setUserList(userList: List<UserModel>?) {
        this.userList = userList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersListAdapter.UserItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.UserItemViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.apply {
            name.text = "${user.first_name} ${user.last_name}"

//            picassoUtil.loadImage(user.avatar!!,imgView)

            Picasso.get()
                .load(user.avatar)
                .placeholder(R.drawable.ic_placeholder_foreground)
                .error(R.drawable.ic_placeholder_error_foreground)
                .into(imgView)

            itemView.setOnClickListener {
                onItemClickListener?.let { it(user) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (UserModel) -> Unit) {
        onItemClickListener = listener
    }

    inner class UserItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<TextView>(R.id.nameTextView)
        val imgView: ImageView = view.findViewById<ImageView>(R.id.userImage)
    }

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}